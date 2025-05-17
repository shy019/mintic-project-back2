package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.SupplierRequestDTO;
import com.mintic.genericstore.dto.response.SupplierResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class SupplierControllerImpl implements com.mintic.genericstore.controller.SupplierController {

	@Qualifier("SupplierServiceImpl")
	private final SupplierService supplierService;

	public SupplierControllerImpl(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public ResponseEntity<List<SupplierResponseDTO>> getSuppliers() throws GenericStoreException {
		List<SupplierResponseDTO> suppliers = supplierService.getSuppliers();
		return new ResponseEntity<>(suppliers, HttpStatus.OK);
	}

	public ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable("SupplierId") Long SupplierId)
			throws GenericStoreException {
		SupplierResponseDTO supplier = supplierService.getSupplierById(SupplierId);
		return new ResponseEntity<>(supplier, HttpStatus.OK);
	}

	public ResponseEntity<SupplierResponseDTO> save(@Valid @RequestBody SupplierRequestDTO supplier)
			throws GenericStoreException {
		SupplierResponseDTO savedSupplier = supplierService.saveSupplier(supplier);
		return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
	}

	public ResponseEntity<SupplierResponseDTO> update(@RequestBody SupplierRequestDTO supplier)
			throws GenericStoreException {
		SupplierResponseDTO updatedSupplier = supplierService.updateSupplier(supplier);
		return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
	}

	public ResponseEntity<SupplierResponseDTO> delete(@PathVariable("SupplierId") Long SupplierId)
			throws GenericStoreException {
		SupplierResponseDTO deletedSupplier = supplierService.deleteSupplier(SupplierId);
		return new ResponseEntity<>(deletedSupplier, HttpStatus.OK);
	}
}
