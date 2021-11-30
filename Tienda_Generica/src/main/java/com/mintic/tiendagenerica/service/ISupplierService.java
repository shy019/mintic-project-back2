package com.mintic.tiendagenerica.service;

import java.util.List;

import com.mintic.tiendagenerica.dto.request.SupplierRequestDTO;
import com.mintic.tiendagenerica.dto.response.SupplierResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;

public interface ISupplierService {
	public List<SupplierResponseDTO> getSuppliers() throws TiendaGenericaException;

	public SupplierResponseDTO getSupplierByCedula(Long nitProveedor) throws TiendaGenericaException;

	public SupplierResponseDTO saveSupplier(SupplierRequestDTO supplier) throws TiendaGenericaException;

	public SupplierResponseDTO deleteSupplier(Long nitProveedor) throws TiendaGenericaException;

	public SupplierResponseDTO updateSupplier(SupplierRequestDTO supplier) throws TiendaGenericaException;

	public SupplierResponseDTO getSupplierByName(String nombreProveedor) throws TiendaGenericaException;

}
