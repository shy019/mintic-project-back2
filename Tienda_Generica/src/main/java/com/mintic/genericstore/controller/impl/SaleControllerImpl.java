package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.controller.SaleController;
import com.mintic.genericstore.dto.request.SaleRequestDTO;
import com.mintic.genericstore.dto.response.SaleResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.SaleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class SaleControllerImpl implements SaleController {

	@Qualifier("SaleServiceImpl")
	private final SaleService saleService;

	public SaleControllerImpl(SaleService saleService) {
		this.saleService = saleService;
	}

	@Override
	public ResponseEntity<List<SaleResponseDTO>> getAllSales() throws GenericStoreException {
		return new ResponseEntity<>(saleService.getSales(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SaleResponseDTO> getSaleById(Long saleCode) throws GenericStoreException {
		return new ResponseEntity<>(saleService.getSaleBySaleCode(saleCode), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SaleResponseDTO> createSale(SaleRequestDTO sale) throws GenericStoreException {
		return new ResponseEntity<>(saleService.saveSale(sale), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SaleResponseDTO> updateSale(SaleRequestDTO sale) throws GenericStoreException {
		return new ResponseEntity<>(saleService.updateSale(sale), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SaleResponseDTO> deleteSale(Long saleCode) throws GenericStoreException {
		return new ResponseEntity<>(saleService.deleteSale(saleCode), HttpStatus.OK);
	}
}
