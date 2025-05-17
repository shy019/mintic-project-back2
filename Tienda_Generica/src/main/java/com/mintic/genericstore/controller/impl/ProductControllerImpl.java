package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.ProductAllRequestDTO;
import com.mintic.genericstore.dto.request.ProductRequestDTO;
import com.mintic.genericstore.dto.response.ProductResponseDTO;
import com.mintic.genericstore.dto.response.ResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class ProductControllerImpl implements com.mintic.genericstore.controller.ProductController {

	@Qualifier("ProductServiceImpl")
	private final ProductService productService;

	public ProductControllerImpl(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts() throws GenericStoreException {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponseDTO> getProduct(Long productCode) throws GenericStoreException {
		return new ResponseEntity<>(productService.getProductByProductCode(productCode), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponseDTO> createProduct(ProductRequestDTO product) throws GenericStoreException {
		return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseDTO> createAllProducts(ProductAllRequestDTO[] products) throws GenericStoreException {
		return new ResponseEntity<>(productService.saveAllProduct(products), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponseDTO> updateProduct(ProductRequestDTO product) throws GenericStoreException {
		return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponseDTO> deleteProduct(Long productCode) throws GenericStoreException {
		return new ResponseEntity<>(productService.deleteProduct(productCode), HttpStatus.OK);
	}
}
