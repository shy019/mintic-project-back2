package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.SaleDetailRequestDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class SaleDetailControllerImpl implements com.mintic.genericstore.controller.SaleDetailController {

	@Qualifier("SaleDetailServiceImpl")
	private final SaleDetailService saleDetailService;

	public SaleDetailControllerImpl(SaleDetailService saleDetailService) {
		this.saleDetailService = saleDetailService;
	}

	@Override
	public ResponseEntity<List<SaleDetailResponseDTO>> getAllSaleDetails() throws GenericStoreException {
		List<SaleDetailResponseDTO> saleDetails = saleDetailService.getSaleDetails();
		return ResponseEntity.ok(saleDetails);
	}

	@Override
	public ResponseEntity<SaleDetailResponseDTO> getSaleDetailBySaleCode(Long saleCode) throws GenericStoreException {
		SaleDetailResponseDTO saleDetail = saleDetailService.getSaleDetailBySaleCode(saleCode);
		return ResponseEntity.ok(saleDetail);
	}

	@Override
	public ResponseEntity<String> saveAllSaleDetails(SaleDetailRequestDTO[] saleDetails) throws GenericStoreException {
		String responseMessage = saleDetailService.saveAllSaleDetails(saleDetails);
		return ResponseEntity.ok(responseMessage);
	}

	@Override
	public ResponseEntity<SaleDetailResponseDTO> updateSaleDetail(SaleDetailRequestDTO saleDetail) throws GenericStoreException {
		SaleDetailResponseDTO updatedSaleDetail = saleDetailService.updateSaleDetail(saleDetail);
		return ResponseEntity.ok(updatedSaleDetail);
	}

	@Override
	public ResponseEntity<SaleDetailResponseDTO> deleteSaleDetail(Long saleCode) throws GenericStoreException {
		SaleDetailResponseDTO deletedSaleDetail = saleDetailService.deleteSaleDetail(saleCode);
		return ResponseEntity.ok(deletedSaleDetail);
	}
}
