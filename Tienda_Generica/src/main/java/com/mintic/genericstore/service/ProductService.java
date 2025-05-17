package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.ProductAllRequestDTO;
import com.mintic.genericstore.dto.request.ProductRequestDTO;
import com.mintic.genericstore.dto.response.ProductResponseDTO;
import com.mintic.genericstore.dto.response.ResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;

import java.util.List;

public interface ProductService {

	List<ProductResponseDTO> getProducts() throws GenericStoreException;

	ProductResponseDTO getProductByProductCode(Long productCode) throws GenericStoreException;

	ResponseDTO saveAllProduct(ProductAllRequestDTO[] products) throws GenericStoreException;

	ProductResponseDTO saveProduct(ProductRequestDTO product) throws GenericStoreException;

	ProductResponseDTO deleteProduct(Long productCode) throws GenericStoreException;

	ProductResponseDTO updateProduct(ProductRequestDTO product) throws GenericStoreException;

	ProductResponseDTO getProductByName(String productName) throws GenericStoreException;

}
