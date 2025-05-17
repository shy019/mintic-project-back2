package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.SupplierRequestDTO;
import com.mintic.genericstore.dto.response.SupplierResponseDTO;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Supplier;
import com.mintic.genericstore.repository.SupplierRepository;
import com.mintic.genericstore.service.SupplierService;
import com.mintic.genericstore.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {

	private final SupplierRepository supplierRepository;

	@Override
	public List<SupplierResponseDTO> getSuppliers() {
		List<Supplier> suppliers = supplierRepository.findAll();
		if (suppliers.isEmpty()) {
			log.warn(SUPPLIERS_NOT_FOUND);
			return Collections.EMPTY_LIST;
		}
		return suppliers.stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public SupplierResponseDTO getSupplierById(Long supplierId) {
		Supplier supplier = findByIdOrThrow(supplierId);
		return toResponseDTO(supplier);
	}

	@Override
	public SupplierResponseDTO getSupplierByName(String name) {
		Supplier supplier = supplierRepository.findBySupplierName(name)
				.orElseThrow(() -> {
					log.error(SUPPLIER_NOT_FOUND_NAME);
					return new NotFoundException(SUPPLIER_NOT_FOUND_NAME);
				});
		return toResponseDTO(supplier);
	}

	@Override
	public SupplierResponseDTO saveSupplier(SupplierRequestDTO request) {
		validateUniqueSupplierId(request.getSupplierId());
		Supplier supplier = toEntity(request);
		return toResponseDTO(supplierRepository.save(supplier));
	}

	@Override
	public SupplierResponseDTO deleteSupplier(Long supplierId) {
		Supplier supplier = findByIdOrThrow(supplierId);
		supplierRepository.deleteBySupplierId(supplierId);
		return toResponseDTO(supplier);
	}

	@Override
	public SupplierResponseDTO updateSupplier(SupplierRequestDTO request) {
		Supplier existingSupplier = findByIdOrThrow(request.getSupplierId());
		updateEntityFields(existingSupplier, request);
		return toResponseDTO(supplierRepository.save(existingSupplier));
	}

	// Private helpers

	private Supplier findByIdOrThrow(Long id) {
		return supplierRepository.findBySupplierId(id)
				.orElseThrow(() -> {
					log.error(SUPPLIER_NOT_FOUND_ID);
                    return new NotFoundException(SUPPLIER_NOT_FOUND_ID);
				});
	}

	private void validateUniqueSupplierId(Long id) {
		if (supplierRepository.existsBySupplierId(id)) {
			log.error(SUPPLIER_NIT_EXISTS);
			throw new NotFoundException(SUPPLIER_NIT_EXISTS);
		}
	}

	private Supplier toEntity(SupplierRequestDTO request) {
		return MapperUtil.mapToDTO(request, Supplier.class);
	}

	private SupplierResponseDTO toResponseDTO(Supplier supplier) {
		return MapperUtil.mapToDTO(supplier, SupplierResponseDTO.class);
	}

	private void updateEntityFields(Supplier supplier, SupplierRequestDTO request) {
		supplier.setSupplierName(request.getSupplierName());
		supplier.setSupplierAddress(request.getSupplierAddress());
		supplier.setSupplierCity(request.getSupplierCity());
		supplier.setSupplierPhone(request.getSupplierPhone());
	}
}
