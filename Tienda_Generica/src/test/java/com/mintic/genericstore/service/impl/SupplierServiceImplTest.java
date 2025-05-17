package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.SupplierRequestDTO;
import com.mintic.genericstore.dto.response.SupplierResponseDTO;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Supplier;
import com.mintic.genericstore.repository.SupplierRepository;
import com.mintic.genericstore.utils.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSuppliers_returnsList_whenFound() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        when(supplierRepository.findAll()).thenReturn(Collections.singletonList(supplier));

        var result = supplierService.getSuppliers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierRepository).findAll();
    }

    @Test
    void getSuppliers_returnsEmptyList_whenNoneFound() {
        when(supplierRepository.findAll()).thenReturn(Collections.emptyList());

        var result = supplierService.getSuppliers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(supplierRepository).findAll();
    }

    @Test
    void getSupplierById_returnsSupplier_whenFound() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        when(supplierRepository.findBySupplierId(1L)).thenReturn(Optional.of(supplier));

        var result = supplierService.getSupplierById(1L);

        assertNotNull(result);
        verify(supplierRepository).findBySupplierId(1L);
    }

    @Test
    void getSupplierById_throwsException_whenNotFound() {
        when(supplierRepository.findBySupplierId(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.getSupplierById(1L));
    }

    @Test
    void getSupplierByName_returnsSupplier_whenFound() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findBySupplierName("test")).thenReturn(Optional.of(supplier));

        var result = supplierService.getSupplierByName("test");

        assertNotNull(result);
        verify(supplierRepository).findBySupplierName("test");
    }

    @Test
    void getSupplierByName_throwsException_whenNotFound() {
        when(supplierRepository.findBySupplierName("notfound")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.getSupplierByName("notfound"));
    }

    @Test
    void saveSupplier_successful_whenUniqueId() {
        SupplierRequestDTO dto = new SupplierRequestDTO();
        dto.setSupplierId(1L);

        Supplier supplier = new Supplier();
        when(supplierRepository.existsBySupplierId(1L)).thenReturn(false);
        when(supplierRepository.save(any())).thenReturn(supplier);

        var result = supplierService.saveSupplier(dto);

        assertNotNull(result);
        verify(supplierRepository).save(any());
    }

    @Test
    void saveSupplier_throwsException_whenDuplicateId() {
        SupplierRequestDTO dto = new SupplierRequestDTO();
        dto.setSupplierId(1L);

        when(supplierRepository.existsBySupplierId(1L)).thenReturn(true);

        assertThrows(NotFoundException.class, () -> supplierService.saveSupplier(dto));
    }

    @Test
    void deleteSupplier_successful_whenFound() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findBySupplierId(1L)).thenReturn(Optional.of(supplier));

        var result = supplierService.deleteSupplier(1L);

        assertNotNull(result);
        verify(supplierRepository).deleteBySupplierId(1L);
    }

    @Test
    void deleteSupplier_throwsException_whenNotFound() {
        when(supplierRepository.findBySupplierId(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.deleteSupplier(1L));
    }

    @Test
    void updateSupplier_successful_whenExists() {
        SupplierRequestDTO dto = new SupplierRequestDTO();
        dto.setSupplierId(1L);
        dto.setSupplierName("Nuevo");
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);

        when(supplierRepository.findBySupplierId(1L)).thenReturn(Optional.of(supplier));
        when(supplierRepository.save(any())).thenReturn(supplier);

        var result = supplierService.updateSupplier(dto);

        assertNotNull(result);
        verify(supplierRepository).save(any());
    }

    @Test
    void updateSupplier_throwsException_whenNotFound() {
        SupplierRequestDTO dto = new SupplierRequestDTO();
        dto.setSupplierId(1L);

        when(supplierRepository.findBySupplierId(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.updateSupplier(dto));
    }
}
