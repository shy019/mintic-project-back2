package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.response.BranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleByBranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Branch;
import com.mintic.genericstore.repository.BranchRepository;
import com.mintic.genericstore.repository.SaleDetailRepository;
import com.mintic.genericstore.service.BranchService;
import com.mintic.genericstore.utils.BranchSalesCalculator;
import com.mintic.genericstore.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service("BranchServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class BranchServiceImpl implements BranchService {

	private final BranchRepository branchRepository;
	private final SaleDetailRepository saleDetailRepository;
	private final BranchSalesCalculator branchSalesCalculator;

	@Override
	@Transactional
	public BranchResponseDTO saveBranch(BranchRequestDTO branchRequest) throws GenericStoreException {

		log.info(SAVING_BRANCH, branchRequest.getBranchName());
		try {

			Branch branch = MapperUtil.mapToDTO(branchRequest, Branch.class);
			Branch savedBranch = branchRepository.save(branch);
			log.info(BRANCH_SAVED_BRANCH, savedBranch.getSaleDetailCode());
			return MapperUtil.mapToDTO(savedBranch, BranchResponseDTO.class);

		} catch (Exception ex) {
			log.error(ERROR_SAVING_BRANCH_BRANCH, ex.getMessage(), ex);
			throw new GenericStoreException(FAILED_TO_SAVE_BRANCH_BRANCH, ex);
		}
	}

	@Override
	public List<SaleByBranchResponseDTO> getSalesByBranch(BranchRequestDTO branchRequest) {

		Long branchId = branchRequest.getSaleDetailCode();
		log.info(FETCHING_SALES_BRANCH, branchId);

		List<SaleDetailResponseDTO> sales = getSaleDetailResponseDTOS(branchId);
		isSalesEmpty(branchRequest, sales);
		SaleByBranchResponseDTO summary = branchSalesCalculator.calculateSalesSummary(sales, branchRequest);
		log.info(SALES_SUMMARY_BRANCH, branchRequest.getBranchName());

		return List.of(summary);
	}

	private static void isSalesEmpty(BranchRequestDTO branchRequest, List<SaleDetailResponseDTO> sales) {

		if (sales.isEmpty()) {
			log.error(NO_SALES_FOUND_BRANCH, branchRequest.getBranchName());
			throw new NotFoundException(NO_SALES_FOUND_FOR_BRANCH + branchRequest.getBranchName());
		}
	}

	private List<SaleDetailResponseDTO> getSaleDetailResponseDTOS(Long branchId) {

        return saleDetailRepository.findAll().stream()
                .filter(sale -> sale.getBranch().getSaleDetailCode().equals(branchId))
                .map(sale -> MapperUtil.mapToDTO(sale, SaleDetailResponseDTO.class))
                .collect(Collectors.toList());
	}
}
