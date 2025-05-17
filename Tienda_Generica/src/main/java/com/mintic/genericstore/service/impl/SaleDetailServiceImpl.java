package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.SaleDetailRequestDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.SaleDetail;
import com.mintic.genericstore.model.Branch;
import com.mintic.genericstore.repository.BranchRepository;
import com.mintic.genericstore.repository.SaleDetailRepository;
import com.mintic.genericstore.service.ExchangeRateService;
import com.mintic.genericstore.service.SaleDetailService;
import com.mintic.genericstore.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleDetailServiceImpl implements SaleDetailService {

	private final SaleDetailRepository saleDetailRepository;
	private final ExchangeRateService exchangeRateService;
	private final BranchRepository branchRepository;

	@Override
	public List<SaleDetailResponseDTO> getSaleDetails() {
		List<SaleDetail> saleDetails = saleDetailRepository.findAll();
		if (saleDetails.isEmpty()) {
			log.warn(NO_SALES_FOUND);
			return Collections.EMPTY_LIST;
		}
		return saleDetails.stream()
				.map((saleDetail -> MapperUtil.mapToDTO(saleDetail, SaleDetailResponseDTO.class)))
				.collect(Collectors.toList());
	}

	@Override
	public SaleDetailResponseDTO getSaleDetailBySaleCode(Long saleCode) {
		SaleDetail saleDetail = findSaleDetailByIdOrThrow(saleCode);
		return MapperUtil.mapToDTO(saleDetail, SaleDetailResponseDTO.class);
	}

	@Override
	public SaleDetailResponseDTO deleteSaleDetail(Long saleCode) {
		SaleDetail saleDetail = findSaleDetailByIdOrThrow(saleCode);
		saleDetailRepository.deleteBySaleDetailId(saleCode);
		return MapperUtil.mapToDTO(saleDetail, SaleDetailResponseDTO.class);
	}

	@Override
	@Transactional
	public SaleDetailResponseDTO updateSaleDetail(SaleDetailRequestDTO dto) {
		dto.setSaleDetailId(generateNextSaleCode());
		SaleDetail saleDetail = buildSaleDetailFromDto(dto);
		SaleDetail saved = saleDetailRepository.save(saleDetail);
		return MapperUtil.mapToDTO(saved, SaleDetailResponseDTO.class);
	}

	@Override
	@Transactional
	public String saveAllSaleDetails(SaleDetailRequestDTO[] saleDetails) throws GenericStoreException {
		try {
			manageDetails(saleDetails);
			return SALE_DETAILS_SAVED_SUCCESS;
		} catch (Exception e) {
			log.error(ERROR_SAVING_SALE_DETAILS, e);
			throw new GenericStoreException(ERROR_SAVING_SALE_DETAILS, e);
		}
	}

	private void manageDetails(SaleDetailRequestDTO[] saleDetails) {
		for (SaleDetailRequestDTO dto : saleDetails) {
			dto.setSaleDetailId(generateNextSaleCode());
			SaleDetail saleDetail = buildSaleDetailFromDto(dto);
			saleDetailRepository.save(saleDetail);
		}
	}

	private SaleDetail findSaleDetailByIdOrThrow(Long id) {
		return saleDetailRepository.findBySaleDetailId(id)
				.orElseThrow(() -> {
					log.error(NO_SALE_DETAIL_FOUND_WITH_CODE + id);
					return new NotFoundException(NO_SALE_DETAIL_FOUND_WITH_CODE + id);
				});
	}

	private SaleDetail buildSaleDetailFromDto(SaleDetailRequestDTO dto) {
		Branch branch = validateBranchExists(dto.getBranch().getSaleDetailCode());
		SaleDetail saleDetail = MapperUtil.mapToDTO(dto, SaleDetail.class);
		setExchange(saleDetail);
		saleDetail.setBranch(branch);
		return saleDetail;
	}

	private Branch validateBranchExists(Long branchId) {
		return branchRepository.findById(branchId)
				.orElseThrow(() -> {
					log.error(BRANCH_NOT_FOUND + branchId);
					return new NotFoundException(BRANCH_NOT_FOUND + branchId);
				});
	}

	private void setExchange(SaleDetail saleDetail) {
		if(saleDetail.getConcurrencyType().equals(CONCURRENCY_TYPE_USD)) {
			Double rate = exchangeRateService.getDollarToPesoRate();
			saleDetail.setSaleValue(saleDetail.getSaleValue() * (CONCURRENCY_RATE / rate));
		}
	}

	private Long generateNextSaleCode() {
		return saleDetailRepository.count() + 1;
	}
}
