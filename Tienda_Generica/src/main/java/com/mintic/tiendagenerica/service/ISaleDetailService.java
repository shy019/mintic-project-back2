package com.mintic.tiendagenerica.service;

import java.util.List;

import com.mintic.tiendagenerica.dto.request.SaleDetailRequestDTO;
import com.mintic.tiendagenerica.dto.response.SaleDetailResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;

public interface ISaleDetailService {

	public List<SaleDetailResponseDTO> getSaleDetails() throws TiendaGenericaException;

	public SaleDetailResponseDTO getSaleDetailByCodigoVenta(Long codigoVenta) throws TiendaGenericaException;

	public String saveAllSaleDetail(SaleDetailRequestDTO[] saleDetail) throws TiendaGenericaException;

	public SaleDetailResponseDTO deleteSaleDetail(Long codigoVenta) throws TiendaGenericaException;

	public SaleDetailResponseDTO updateSaleDetail(SaleDetailRequestDTO saleDetail) throws TiendaGenericaException;

}
