package com.mintic.tiendagenerica.service;

import java.util.List;

import com.mintic.tiendagenerica.dto.request.SaleRequestDTO;
import com.mintic.tiendagenerica.dto.response.SaleResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;

public interface ISaleService {
	
	public List<SaleResponseDTO> getSales() throws TiendaGenericaException;

	public SaleResponseDTO getSaleByCodigoVenta(Long codigoVenta) throws TiendaGenericaException;

	public SaleResponseDTO saveSale(SaleRequestDTO sale) throws TiendaGenericaException;

	public SaleResponseDTO deleteSale(Long codigoVenta) throws TiendaGenericaException;

	public SaleResponseDTO updateSale(SaleRequestDTO sale) throws TiendaGenericaException;

}
