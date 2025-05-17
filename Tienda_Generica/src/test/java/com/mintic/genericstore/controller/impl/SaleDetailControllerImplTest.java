package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.SaleDetailRequestDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.SaleDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleDetailControllerImplTest {

    @Mock
    private SaleDetailService saleDetailService;

    @InjectMocks
    private SaleDetailControllerImpl controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSaleDetails_returnsList() throws GenericStoreException {
        when(saleDetailService.getSaleDetails()).thenReturn(List.of(new SaleDetailResponseDTO()));

        ResponseEntity<List<SaleDetailResponseDTO>> response = controller.getAllSaleDetails();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getSaleDetailBySaleCode_returnsDetail() throws GenericStoreException {
        SaleDetailResponseDTO dto = new SaleDetailResponseDTO();
        when(saleDetailService.getSaleDetailBySaleCode(1L)).thenReturn(dto);

        ResponseEntity<SaleDetailResponseDTO> response = controller.getSaleDetailBySaleCode(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void saveAllSaleDetails_returnsSuccessMessage() throws GenericStoreException {
        SaleDetailRequestDTO[] request = new SaleDetailRequestDTO[1];
        when(saleDetailService.saveAllSaleDetails(request)).thenReturn("Saved");

        ResponseEntity<String> response = controller.saveAllSaleDetails(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Saved", response.getBody());
    }

    @Test
    void updateSaleDetail_returnsUpdatedDetail() throws GenericStoreException {
        SaleDetailRequestDTO dto = new SaleDetailRequestDTO();
        SaleDetailResponseDTO updated = new SaleDetailResponseDTO();
        when(saleDetailService.updateSaleDetail(dto)).thenReturn(updated);

        ResponseEntity<SaleDetailResponseDTO> response = controller.updateSaleDetail(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updated, response.getBody());
    }

    @Test
    void deleteSaleDetail_returnsDeletedDetail() throws GenericStoreException {
        SaleDetailResponseDTO deleted = new SaleDetailResponseDTO();
        when(saleDetailService.deleteSaleDetail(1L)).thenReturn(deleted);

        ResponseEntity<SaleDetailResponseDTO> response = controller.deleteSaleDetail(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(deleted, response.getBody());
    }
}
