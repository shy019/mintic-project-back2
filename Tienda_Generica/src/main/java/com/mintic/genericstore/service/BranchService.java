package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.response.BranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleByBranchResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;

import java.util.List;

public interface BranchService {

	BranchResponseDTO saveBranch(BranchRequestDTO branch) throws GenericStoreException;

	List<SaleByBranchResponseDTO> getSalesByBranch(BranchRequestDTO branch) throws GenericStoreException;
}
