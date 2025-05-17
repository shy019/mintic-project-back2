package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.SaleDetailRequestDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;

import java.util.List;

public interface SaleDetailService {

	List<SaleDetailResponseDTO> getSaleDetails() throws GenericStoreException;

	SaleDetailResponseDTO getSaleDetailBySaleCode(Long saleCode) throws GenericStoreException;

	String saveAllSaleDetails(SaleDetailRequestDTO[] saleDetails) throws GenericStoreException;

	SaleDetailResponseDTO deleteSaleDetail(Long saleCode) throws GenericStoreException;

	SaleDetailResponseDTO updateSaleDetail(SaleDetailRequestDTO saleDetail) throws GenericStoreException;
}
