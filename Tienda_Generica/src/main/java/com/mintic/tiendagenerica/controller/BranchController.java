package com.mintic.tiendagenerica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.tiendagenerica.dto.request.BranchRequestDTO;
import com.mintic.tiendagenerica.dto.response.BranchResponseDTO;
import com.mintic.tiendagenerica.dto.response.SaleDetailResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.service.IBranchService;

@RestController
@RequestMapping("/api/tiendagenerica/branch")
@CrossOrigin(origins = "*", maxAge = 3600L)

public class BranchController {

	@Qualifier("BranchService")
	IBranchService iBranchService;

	public BranchController(IBranchService iBranchService) {
		this.iBranchService = iBranchService;

	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/JSON" }, produces = {
			"application/JSON" })
	public ResponseEntity<BranchResponseDTO> save(@Valid @RequestBody BranchRequestDTO branch)
			throws TiendaGenericaException {

		return new ResponseEntity<BranchResponseDTO>(iBranchService.saveBranch(branch), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/sales", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<List<SaleDetailResponseDTO>> getSalesByBranch(@Valid @RequestBody BranchRequestDTO branch)
			throws TiendaGenericaException {
		return new ResponseEntity<List<SaleDetailResponseDTO>>(iBranchService.getSalesByBranch(branch), HttpStatus.OK);
	}

}
