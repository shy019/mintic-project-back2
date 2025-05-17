package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.SaleRequestDTO;
import com.mintic.genericstore.dto.response.SaleResponseDTO;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Sale;
import com.mintic.genericstore.model.User;
import com.mintic.genericstore.repository.SaleRepository;
import com.mintic.genericstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleServiceImplTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SaleServiceImpl saleService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSales_returnsList_whenSalesExist() {
        Sale sale = new Sale();
        sale.setSaleId(1L);
        when(saleRepository.findAll()).thenReturn(List.of(sale));

        List<SaleResponseDTO> result = saleService.getSales();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(saleRepository).findAll();
    }

    @Test
    void getSales_returnsEmptyList_whenNoSales() {
        when(saleRepository.findAll()).thenReturn(Collections.emptyList());

        List<SaleResponseDTO> result = saleService.getSales();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(saleRepository).findAll();
    }

    @Test
    void getSaleBySaleCode_returnsDTO_whenFound() {
        Sale sale = new Sale();
        when(saleRepository.findBySaleId(10L)).thenReturn(Optional.of(sale));

        SaleResponseDTO result = saleService.getSaleBySaleCode(10L);

        assertNotNull(result);
        verify(saleRepository).findBySaleId(10L);
    }

    @Test
    void getSaleBySaleCode_throwsException_whenNotFound() {
        when(saleRepository.findBySaleId(10L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> saleService.getSaleBySaleCode(10L));
    }

    @Test
    void saveSale_successful_whenUserExists() {
        SaleRequestDTO request = new SaleRequestDTO();
        request.setUserId(1L);
        User user = new User();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(saleRepository.count()).thenReturn(0L);
        when(saleRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        SaleResponseDTO result = saleService.saveSale(request);

        assertNotNull(result);
        verify(userRepository).findById(1L);
        verify(saleRepository).save(any());
    }

    @Test
    void saveSale_throwsException_whenUserNotFound() {
        SaleRequestDTO request = new SaleRequestDTO();
        request.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> saleService.saveSale(request));
    }

    @Test
    void deleteSale_successful_whenFound() {
        Sale sale = new Sale();
        when(saleRepository.findBySaleId(5L)).thenReturn(Optional.of(sale));

        SaleResponseDTO result = saleService.deleteSale(5L);

        assertNotNull(result);
        verify(saleRepository).deleteBySaleId(5L);
    }

    @Test
    void deleteSale_throwsException_whenNotFound() {
        when(saleRepository.findBySaleId(5L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> saleService.deleteSale(5L));
    }

    @Test
    void updateSale_successful_whenUserExists() {
        SaleRequestDTO request = new SaleRequestDTO();
        request.setUserId(1L);
        User user = new User();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(saleRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        SaleResponseDTO result = saleService.updateSale(request);

        assertNotNull(result);
        verify(userRepository).findById(1L);
        verify(saleRepository).save(any());
    }

    @Test
    void updateSale_throwsException_whenUserNotFound() {
        SaleRequestDTO request = new SaleRequestDTO();
        request.setUserId(99L);

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> saleService.updateSale(request));
    }
}
