package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.ProductAllRequestDTO;
import com.mintic.genericstore.dto.request.ProductRequestDTO;
import com.mintic.genericstore.dto.response.ProductResponseDTO;
import com.mintic.genericstore.dto.response.ResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerImplTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductControllerImpl productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_success() throws GenericStoreException {
        ProductResponseDTO product1 = new ProductResponseDTO();
        product1.setProductId(1L);

        ProductResponseDTO product2 = new ProductResponseDTO();
        product2.setProductId(2L);

        when(productService.getProducts()).thenReturn(List.of(product1, product2));

        ResponseEntity<List<ProductResponseDTO>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void getProduct_success() throws GenericStoreException {
        Long code = 5L;
        ProductResponseDTO product = new ProductResponseDTO();
        product.setProductId(code);

        when(productService.getProductByProductCode(code)).thenReturn(product);

        ResponseEntity<ProductResponseDTO> response = productController.getProduct(code);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(code, Objects.requireNonNull(response.getBody()).getProductId());
    }

    @Test
    void createProduct_success() throws GenericStoreException {
        ProductRequestDTO request = new ProductRequestDTO();
        ProductResponseDTO created = new ProductResponseDTO();
        created.setProductId(10L);

        when(productService.saveProduct(request)).thenReturn(created);

        ResponseEntity<ProductResponseDTO> response = productController.createProduct(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10L, Objects.requireNonNull(response.getBody()).getProductId());
    }

    @Test
    void createAllProducts_success() throws GenericStoreException {
        ProductAllRequestDTO[] requestArray = new ProductAllRequestDTO[] {
                new ProductAllRequestDTO(), new ProductAllRequestDTO()
        };

        ResponseDTO result = new ResponseDTO();
        result.setMessage("2 products created");

        when(productService.saveAllProduct(requestArray)).thenReturn(result);

        ResponseEntity<ResponseDTO> response = productController.createAllProducts(requestArray);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("2 products created", Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void updateProduct_success() throws GenericStoreException {
        ProductRequestDTO request = new ProductRequestDTO();
        ProductResponseDTO updated = new ProductResponseDTO();
        updated.setProductId(15L);

        when(productService.updateProduct(request)).thenReturn(updated);

        ResponseEntity<ProductResponseDTO> response = productController.updateProduct(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(15L, Objects.requireNonNull(response.getBody()).getProductId());
    }

    @Test
    void deleteProduct_success() throws GenericStoreException {
        Long code = 8L;
        ProductResponseDTO deleted = new ProductResponseDTO();
        deleted.setProductId(code);

        when(productService.deleteProduct(code)).thenReturn(deleted);

        ResponseEntity<ProductResponseDTO> response = productController.deleteProduct(code);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(code, Objects.requireNonNull(response.getBody()).getProductId());
    }
}
