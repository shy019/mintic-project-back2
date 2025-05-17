package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.SupplierRequestDTO;
import com.mintic.genericstore.dto.response.SupplierResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierControllerImplTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierControllerImpl controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSuppliers_returnsList() throws GenericStoreException {
        when(supplierService.getSuppliers()).thenReturn(List.of(new SupplierResponseDTO()));

        var response = controller.getSuppliers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getSupplier_returnsDTO() throws GenericStoreException {
        SupplierResponseDTO dto = new SupplierResponseDTO();
        when(supplierService.getSupplierById(1L)).thenReturn(dto);

        var response = controller.getSupplier(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void save_returnsCreatedSupplier() throws GenericStoreException {
        SupplierRequestDTO request = new SupplierRequestDTO();
        SupplierResponseDTO responseDTO = new SupplierResponseDTO();
        when(supplierService.saveSupplier(request)).thenReturn(responseDTO);

        var response = controller.save(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void update_returnsUpdatedSupplier() throws GenericStoreException {
        SupplierRequestDTO request = new SupplierRequestDTO();
        SupplierResponseDTO updated = new SupplierResponseDTO();
        when(supplierService.updateSupplier(request)).thenReturn(updated);

        var response = controller.update(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updated, response.getBody());
    }

    @Test
    void delete_returnsDeletedSupplier() throws GenericStoreException {
        SupplierResponseDTO deleted = new SupplierResponseDTO();
        when(supplierService.deleteSupplier(1L)).thenReturn(deleted);

        var response = controller.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(deleted, response.getBody());
    }
}
