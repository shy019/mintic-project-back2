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

import com.mintic.tiendagenerica.dto.request.SaleRequestDTO;
import com.mintic.tiendagenerica.dto.response.SaleResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.service.ISaleService;

@RestController
@RequestMapping("/api/tiendagenerica/sale")
@CrossOrigin(origins = "*", maxAge = 3600L)

public class SaleController {

	@Qualifier("SaleService")
	ISaleService iSaleService;

	public SaleController(ISaleService iSaleService) {
		this.iSaleService = iSaleService;

	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<SaleResponseDTO>> getSale() throws TiendaGenericaException {
		return new ResponseEntity<List<SaleResponseDTO>>(iSaleService.getSales(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{codigoVenta}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<SaleResponseDTO> getSale(@PathVariable("codigoVenta") Long codigoVenta)
			throws TiendaGenericaException {
		return new ResponseEntity<SaleResponseDTO>(iSaleService.getSaleByCodigoVenta(codigoVenta), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/JSON" }, produces = {
			"application/JSON" })
	public ResponseEntity<SaleResponseDTO> save(@Valid @RequestBody SaleRequestDTO sale)
			throws TiendaGenericaException {

		return new ResponseEntity<SaleResponseDTO>(iSaleService.saveSale(sale), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/JSON" }, consumes = {
			"application/JSON" })
	public ResponseEntity<SaleResponseDTO> editar(@RequestBody SaleRequestDTO sale) throws TiendaGenericaException {
		return new ResponseEntity<SaleResponseDTO>(iSaleService.updateSale(sale), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{codigoVenta}", method = RequestMethod.DELETE, produces = { "application/JSON" })
	public ResponseEntity<SaleResponseDTO> delete(@PathVariable("codigoVenta") Long codigoVenta)
			throws TiendaGenericaException {
		return new ResponseEntity<SaleResponseDTO>(iSaleService.deleteSale(codigoVenta), HttpStatus.OK);
	}

}
