package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.request.SaleDetailRequestDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Branch;
import com.mintic.genericstore.model.SaleDetail;
import com.mintic.genericstore.repository.BranchRepository;
import com.mintic.genericstore.repository.SaleDetailRepository;
import com.mintic.genericstore.service.ExchangeRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SaleDetailServiceImplTest {

    @Mock
    private SaleDetailRepository saleDetailRepository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private SaleDetailServiceImpl saleDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSaleDetails_returnsList() {
        SaleDetail detail = new SaleDetail();
        when(saleDetailRepository.findAll()).thenReturn(List.of(detail));

        List<SaleDetailResponseDTO> result = saleDetailService.getSaleDetails();

        assertNotNull(result);
        verify(saleDetailRepository).findAll();
    }

    @Test
    void getSaleDetails_returnsEmptyList() {
        when(saleDetailRepository.findAll()).thenReturn(Collections.emptyList());

        List<SaleDetailResponseDTO> result = saleDetailService.getSaleDetails();

        assertTrue(result.isEmpty());
    }

    @Test
    void getSaleDetailBySaleCode_found() {
        SaleDetail detail = new SaleDetail();
        when(saleDetailRepository.findBySaleDetailId(1L)).thenReturn(Optional.of(detail));

        SaleDetailResponseDTO result = saleDetailService.getSaleDetailBySaleCode(1L);

        assertNotNull(result);
        verify(saleDetailRepository).findBySaleDetailId(1L);
    }

    @Test
    void getSaleDetailBySaleCode_notFound() {
        when(saleDetailRepository.findBySaleDetailId(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> saleDetailService.getSaleDetailBySaleCode(1L));
    }

    @Test
    void deleteSaleDetail_success() {
        SaleDetail detail = new SaleDetail();
        when(saleDetailRepository.findBySaleDetailId(1L)).thenReturn(Optional.of(detail));

        SaleDetailResponseDTO result = saleDetailService.deleteSaleDetail(1L);

        assertNotNull(result);
        verify(saleDetailRepository).deleteBySaleDetailId(1L);
    }

    @Test
    void saveAllSaleDetails_success() throws GenericStoreException {
        SaleDetailRequestDTO dto = new SaleDetailRequestDTO();
        SaleDetail dto2 = new SaleDetail();
        BranchRequestDTO branch = new BranchRequestDTO();
        branch.setSaleDetailCode(1L);
        Branch branch2 = new Branch();
        branch2.setSaleDetailCode(1L);
        dto.setBranch(branch);
        dto.setConcurrencyType("USD");
        dto2.setBranch(branch2);

        when(branchRepository.findById(anyLong())).thenReturn(Optional.of(branch2));

        String result = saleDetailService.saveAllSaleDetails(List.of(dto).toArray(new SaleDetailRequestDTO[0]));

        assertNotNull(result);
        verify(saleDetailRepository, atLeastOnce()).save(any(SaleDetail.class));
    }

    @Test
    void saveAllSaleDetails_throwsException() {
        assertThrows(GenericStoreException.class, () -> {
            saleDetailService.saveAllSaleDetails(null);
        });
    }
}
