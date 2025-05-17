package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.response.BranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleByBranchResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.BranchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class BranchControllerImpl implements com.mintic.genericstore.controller.BranchController {

	@Qualifier("BranchServiceImpl")
	private final BranchService branchService;

	public BranchControllerImpl(BranchService branchService) {
		this.branchService = branchService;
	}

	@Override
	public ResponseEntity<BranchResponseDTO> saveBranch(BranchRequestDTO branchDTO) throws GenericStoreException {
		BranchResponseDTO response = branchService.saveBranch(branchDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<SaleByBranchResponseDTO>> getSalesByBranch(BranchRequestDTO branchDTO) throws GenericStoreException {
		List<SaleByBranchResponseDTO> sales = branchService.getSalesByBranch(branchDTO);
		return new ResponseEntity<>(sales, HttpStatus.OK);
	}
}
