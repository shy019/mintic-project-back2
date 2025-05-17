package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.response.BranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleByBranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Branch;
import com.mintic.genericstore.model.SaleDetail;
import com.mintic.genericstore.repository.BranchRepository;
import com.mintic.genericstore.repository.SaleDetailRepository;
import com.mintic.genericstore.utils.BranchSalesCalculator;
import com.mintic.genericstore.utils.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchServiceImplTest {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private SaleDetailRepository saleDetailRepository;

    @Mock
    private BranchSalesCalculator branchSalesCalculator;

    @InjectMocks
    private BranchServiceImpl branchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBranch_success() throws GenericStoreException {
        BranchRequestDTO request = new BranchRequestDTO();
        request.setBranchName("Sucursal 1");

        Branch branch = new Branch();
        branch.setBranchName("Sucursal 1");

        Branch saved = new Branch();
        saved.setBranchName("Sucursal 1");
        saved.setSaleDetailCode(1L);

        BranchResponseDTO expectedResponse = new BranchResponseDTO();
        expectedResponse.setBranchName("Sucursal 1");
        expectedResponse.setSaleDetailCode(1L);

        try (MockedStatic<MapperUtil> mapperMock = mockStatic(MapperUtil.class)) {
            mapperMock.when(() -> MapperUtil.mapToDTO(request, Branch.class)).thenReturn(branch);
            when(branchRepository.save(branch)).thenReturn(saved);
            mapperMock.when(() -> MapperUtil.mapToDTO(saved, BranchResponseDTO.class)).thenReturn(expectedResponse);

            BranchResponseDTO result = branchService.saveBranch(request);

            assertEquals(expectedResponse.getBranchName(), result.getBranchName());
            assertEquals(expectedResponse.getSaleDetailCode(), result.getSaleDetailCode());
        }
    }

    @Test
    void saveBranch_shouldThrowGenericStoreException() {
        BranchRequestDTO request = new BranchRequestDTO();
        request.setBranchName("Sucursal 2");

        try (MockedStatic<MapperUtil> mapperMock = mockStatic(MapperUtil.class)) {
            mapperMock.when(() -> MapperUtil.mapToDTO(request, Branch.class)).thenThrow(new RuntimeException("mapping failed"));

            assertThrows(GenericStoreException.class, () -> branchService.saveBranch(request));
        }
    }

    @Test
    void getSalesByBranch_success() {
        BranchRequestDTO request = new BranchRequestDTO();
        request.setBranchName("Sucursal A");
        request.setSaleDetailCode(1L);

        BranchResponseDTO branch = new BranchResponseDTO();
        branch.setSaleDetailCode(1L);

        Branch branch2 = new Branch();
        branch2.setSaleDetailCode(1L);

        SaleDetailResponseDTO sale1 = new SaleDetailResponseDTO();
        sale1.setBranch(branch);

        SaleDetail sale2 = new SaleDetail();
        sale2.setBranch(branch2);

        when(saleDetailRepository.findAll()).thenReturn(List.of(sale2));

        SaleByBranchResponseDTO summary = new SaleByBranchResponseDTO();
        summary.setBranch("Sucursal A");

        when(branchSalesCalculator.calculateSalesSummary(anyList(), eq(request))).thenReturn(summary);

        List<SaleByBranchResponseDTO> result = branchService.getSalesByBranch(request);

        assertEquals(1, result.size());
        assertEquals("Sucursal A", result.get(0).getBranch());
    }

    @Test
    void getSalesByBranch_shouldThrowNotFoundException_whenNoSales() {
        BranchRequestDTO request = new BranchRequestDTO();
        request.setBranchName("Sucursal Vacía");
        request.setSaleDetailCode(99L);

        when(saleDetailRepository.findAll()).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> branchService.getSalesByBranch(request));
    }
}
