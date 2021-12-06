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

import com.mintic.tiendagenerica.dto.request.SaleDetailRequestDTO;
import com.mintic.tiendagenerica.dto.response.SaleDetailResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.service.ISaleDetailService;

@RestController
@RequestMapping("/api/tiendagenerica/saledetail")
@CrossOrigin(origins = "*", maxAge = 3600L)

public class SaleDetailController {

	@Qualifier("SaleDetailService")
	ISaleDetailService iSaleDetailService;

	public SaleDetailController(ISaleDetailService iSaleDetailService) {
		this.iSaleDetailService = iSaleDetailService;

	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<SaleDetailResponseDTO>> getSaleDetail() throws TiendaGenericaException {
		return new ResponseEntity<List<SaleDetailResponseDTO>>(iSaleDetailService.getSaleDetails(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{codigoVenta}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<SaleDetailResponseDTO> getSaleDetail(@PathVariable("codigoVenta") Long codigoVenta)
			throws TiendaGenericaException {
		return new ResponseEntity<SaleDetailResponseDTO>(iSaleDetailService.getSaleDetailByCodigoVenta(codigoVenta),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/JSON" }, produces = {
			"application/JSON" })
	public ResponseEntity<String> saveAll(@Valid @RequestBody SaleDetailRequestDTO[] saleDetail)
			throws TiendaGenericaException {
		return new ResponseEntity<String>(iSaleDetailService.saveAllSaleDetail(saleDetail), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/JSON" }, consumes = {
			"application/JSON" })
	public ResponseEntity<SaleDetailResponseDTO> editar(@RequestBody SaleDetailRequestDTO saleDetail)
			throws TiendaGenericaException {
		return new ResponseEntity<SaleDetailResponseDTO>(iSaleDetailService.updateSaleDetail(saleDetail),
				HttpStatus.OK);

	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{codigoVenta}", method = RequestMethod.DELETE, produces = { "application/JSON" })
	public ResponseEntity<SaleDetailResponseDTO> delete(@PathVariable("codigoVenta") Long codigoVenta)
			throws TiendaGenericaException {
		return new ResponseEntity<SaleDetailResponseDTO>(iSaleDetailService.deleteSaleDetail(codigoVenta),
				HttpStatus.OK);
	}

}
