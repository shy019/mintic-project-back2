package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.response.BranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleByBranchResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchControllerImplTest {

    @Mock
    private BranchService branchService;

    @InjectMocks
    private BranchControllerImpl controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBranch_returnsSavedBranch() throws GenericStoreException {
        BranchRequestDTO request = new BranchRequestDTO();
        BranchResponseDTO responseDTO = new BranchResponseDTO();

        when(branchService.saveBranch(request)).thenReturn(responseDTO);

        ResponseEntity<BranchResponseDTO> response = controller.saveBranch(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void getSalesByBranch_returnsSalesList() throws GenericStoreException {
        BranchRequestDTO request = new BranchRequestDTO();
        List<SaleByBranchResponseDTO> sales = List.of(new SaleByBranchResponseDTO());

        when(branchService.getSalesByBranch(request)).thenReturn(sales);

        ResponseEntity<List<SaleByBranchResponseDTO>> response = controller.getSalesByBranch(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
}
