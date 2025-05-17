package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.SupplierRequestDTO;
import com.mintic.genericstore.dto.response.SupplierResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;

import java.util.List;

public interface SupplierService {

	List<SupplierResponseDTO> getSuppliers() throws GenericStoreException;

	SupplierResponseDTO getSupplierById(Long SupplierId) throws GenericStoreException;

	SupplierResponseDTO saveSupplier(SupplierRequestDTO supplier) throws GenericStoreException;

	SupplierResponseDTO deleteSupplier(Long SupplierId) throws GenericStoreException;

	SupplierResponseDTO updateSupplier(SupplierRequestDTO supplier) throws GenericStoreException;

	SupplierResponseDTO getSupplierByName(String supplierName) throws GenericStoreException;
}
