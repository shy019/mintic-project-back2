package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.SaleRequestDTO;
import com.mintic.genericstore.dto.response.SaleResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleControllerImplTest {

    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleControllerImpl saleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSales_success() throws GenericStoreException {
        SaleResponseDTO sale1 = new SaleResponseDTO();
        sale1.setSaleId(1L);

        SaleResponseDTO sale2 = new SaleResponseDTO();
        sale2.setSaleId(2L);

        when(saleService.getSales()).thenReturn(List.of(sale1, sale2));

        ResponseEntity<List<SaleResponseDTO>> response = saleController.getAllSales();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void getSaleById_success() throws GenericStoreException {
        Long saleCode = 1L;
        SaleResponseDTO expectedSale = new SaleResponseDTO();
        expectedSale.setSaleId(saleCode);

        when(saleService.getSaleBySaleCode(saleCode)).thenReturn(expectedSale);

        ResponseEntity<SaleResponseDTO> response = saleController.getSaleById(saleCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saleCode, Objects.requireNonNull(response.getBody()).getSaleId());
    }

    @Test
    void createSale_success() throws GenericStoreException {
        SaleRequestDTO request = new SaleRequestDTO();
        SaleResponseDTO createdSale = new SaleResponseDTO();
        createdSale.setSaleId(10L);

        when(saleService.saveSale(request)).thenReturn(createdSale);

        ResponseEntity<SaleResponseDTO> response = saleController.createSale(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10L, Objects.requireNonNull(response.getBody()).getSaleId());
    }

    @Test
    void updateSale_success() throws GenericStoreException {
        SaleRequestDTO request = new SaleRequestDTO();
        SaleResponseDTO updatedSale = new SaleResponseDTO();
        updatedSale.setSaleId(20L);

        when(saleService.updateSale(request)).thenReturn(updatedSale);

        ResponseEntity<SaleResponseDTO> response = saleController.updateSale(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(20L, Objects.requireNonNull(response.getBody()).getSaleId());
    }

    @Test
    void deleteSale_success() throws GenericStoreException {
        Long saleCode = 5L;
        SaleResponseDTO deletedSale = new SaleResponseDTO();
        deletedSale.setSaleId(saleCode);

        when(saleService.deleteSale(saleCode)).thenReturn(deletedSale);

        ResponseEntity<SaleResponseDTO> response = saleController.deleteSale(saleCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saleCode, Objects.requireNonNull(response.getBody()).getSaleId());
    }
}
