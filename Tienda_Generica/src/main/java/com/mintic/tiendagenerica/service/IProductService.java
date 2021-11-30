package com.mintic.tiendagenerica.service;

import java.util.List;

import com.mintic.tiendagenerica.dto.request.ProductRequestDTO;
import com.mintic.tiendagenerica.dto.response.ProductResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;

public interface IProductService {
	
	public List<ProductResponseDTO> getProducts() throws TiendaGenericaException;

	public ProductResponseDTO getProductByCodigoProducto(Long codigoProducto) throws TiendaGenericaException;

	public ProductResponseDTO saveProduct(ProductRequestDTO Product) throws TiendaGenericaException;

	public ProductResponseDTO deleteProduct(Long codigoProducto) throws TiendaGenericaException;

	public ProductResponseDTO updateProduct(ProductRequestDTO Product) throws TiendaGenericaException;

	public ProductResponseDTO getProductByName(String nombreProducto) throws TiendaGenericaException;

}
