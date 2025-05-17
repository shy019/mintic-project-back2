package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.SaleRequestDTO;
import com.mintic.genericstore.dto.response.SaleResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;

import java.util.List;

public interface SaleService {

	List<SaleResponseDTO> getSales() throws GenericStoreException;

	SaleResponseDTO getSaleBySaleCode(Long saleCode) throws GenericStoreException;

	SaleResponseDTO saveSale(SaleRequestDTO sale) throws GenericStoreException;

	SaleResponseDTO deleteSale(Long saleCode) throws GenericStoreException;

	SaleResponseDTO updateSale(SaleRequestDTO sale) throws GenericStoreException;
}
