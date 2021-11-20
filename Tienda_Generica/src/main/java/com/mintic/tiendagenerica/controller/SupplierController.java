package com.mintic.tiendagenerica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.tiendagenerica.dto.request.SupplierRequestDTO;
import com.mintic.tiendagenerica.dto.response.SupplierResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.service.ISupplierService;

@RestController
@RequestMapping("/api/tiendagenerica/supplier")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class SupplierController {

	@Qualifier("SupplierService")
	ISupplierService iSupplierService;

	public SupplierController(ISupplierService iSupplierService) {
		this.iSupplierService = iSupplierService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<SupplierResponseDTO>> getSuppliers() throws TiendaGenericaException {
		return new ResponseEntity<List<SupplierResponseDTO>>(iSupplierService.getSuppliers(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{nitProveedor}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable("nitProveedor") Long nitProveedor)
			throws TiendaGenericaException {
		return new ResponseEntity<SupplierResponseDTO>(iSupplierService.getSupplierByCedula(nitProveedor), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/JSON" }, produces = {
			"application/JSON" })
	public ResponseEntity<SupplierResponseDTO> save(@Valid @RequestBody SupplierRequestDTO supplier)
			throws TiendaGenericaException {

		return new ResponseEntity<SupplierResponseDTO>(iSupplierService.saveSupplier(supplier), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/JSON" }, consumes = {
			"application/JSON" })
	public ResponseEntity<SupplierResponseDTO> editar(@RequestBody SupplierRequestDTO supplier)
			throws TiendaGenericaException {
		return new ResponseEntity<SupplierResponseDTO>(iSupplierService.updateSupplier(supplier), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{nitProveedor}", method = RequestMethod.DELETE, produces = { "application/JSON" })
	public ResponseEntity<SupplierResponseDTO> delete(@PathVariable("nitProveedor") long nitProveedor)
			throws TiendaGenericaException {
		return new ResponseEntity<SupplierResponseDTO>(iSupplierService.deleteSupplier(nitProveedor), HttpStatus.OK);
	}
}