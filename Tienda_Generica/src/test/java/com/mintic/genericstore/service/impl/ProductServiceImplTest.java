
package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.ProductAllRequestDTO;
import com.mintic.genericstore.dto.request.ProductRequestDTO;
import com.mintic.genericstore.dto.request.SupplierRequestDTO;
import com.mintic.genericstore.dto.response.ProductResponseDTO;
import com.mintic.genericstore.dto.response.ResponseDTO;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Product;
import com.mintic.genericstore.model.Supplier;
import com.mintic.genericstore.repository.ProductRepository;
import com.mintic.genericstore.repository.SupplierRepository;
import com.mintic.genericstore.utils.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private ProductValidator productValidator;

    @InjectMocks
    private ProductServiceImpl productService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProducts_returnsList_whenProductsExist() {
        Product product = new Product();
        when(productRepository.findAll()).thenReturn(List.of(product));

        var result = productService.getProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getProductById_returnsDTO_whenFound() {
        Product product = new Product();
        when(productRepository.findByProductId(1L)).thenReturn(Optional.of(product));

        var result = productService.getProductByProductCode(1L);

        assertNotNull(result);
    }

    @Test
    void getProductById_throwsException_whenNotFound() {
        when(productRepository.findByProductId(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.getProductByProductCode(1L));
    }

    @Test
    void getProductByName_returnsDTO_whenFound() {
        Product product = new Product();
        when(productRepository.findByProductName("test")).thenReturn(Optional.of(product));

        var result = productService.getProductByName("test");

        assertNotNull(result);
    }

    @Test
    void getProductByName_throwsException_whenNotFound() {
        when(productRepository.findByProductName("test")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.getProductByName("test"));
    }

    @Test
    void saveProduct_succeeds_whenValid() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductId(1L);
        SupplierRequestDTO supplierRequestDTO = new SupplierRequestDTO();
        supplierRequestDTO.setSupplierId(1L);
        dto.setSupplier(supplierRequestDTO);
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        when(productRepository.findByProductId(any())).thenReturn(Optional.of(new Product()));
        when(supplierRepository.findBySupplierId(1L)).thenReturn(Optional.of(supplier));
        when(productValidator.validateSupplierExists(any())).thenReturn(supplier);
        when(productRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        var result = productService.saveProduct(dto);

        assertNotNull(result);
        verify(productRepository).save(any());
    }

    @Test
    void updateProduct_succeeds_whenProductExists() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductId(1L);
        SupplierRequestDTO supplierRequestDTO = new SupplierRequestDTO();
        supplierRequestDTO.setSupplierId(1L);
        dto.setSupplier(supplierRequestDTO);
        Supplier supplier = new Supplier();
        Product product = new Product();

        when(productRepository.findByProductId(1L)).thenReturn(Optional.of(product));
        when(productValidator.validateSupplierExists(any())).thenReturn(supplier);
        when(productRepository.save(any())).thenReturn(product);

        var result = productService.updateProduct(dto);

        assertNotNull(result);
        verify(productRepository).save(any());
    }

    @Test
    void deleteProduct_succeeds_whenFound() {
        Product product = new Product();
        when(productRepository.findByProductId(1L)).thenReturn(Optional.of(product));

        var result = productService.deleteProduct(1L);

        assertNotNull(result);
        verify(productRepository).deleteByProductId(1L);
    }

    @Test
    void deleteProduct_throwsException_whenNotFound() {
        when(productRepository.findByProductId(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.deleteProduct(1L));
    }

    @Test
    void saveAllProduct_handlesValidAndInvalid() {
        ProductAllRequestDTO valid = new ProductAllRequestDTO();
        valid.setProductCode(1L);
        valid.setSupplierId(1L);

        ProductAllRequestDTO invalid = new ProductAllRequestDTO();
        invalid.setProductCode(2L);
        invalid.setSupplierId(2L);

        when(productRepository.findByProductId(1L)).thenReturn(Optional.empty());
        when(productValidator.validateSupplierOptional(1L)).thenReturn(Optional.of(new Supplier()));
        when(productRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        when(productRepository.findByProductId(2L)).thenReturn(Optional.of(new Product()));
        when(productValidator.validateSupplierOptional(2L)).thenReturn(Optional.empty());

        ResponseDTO result = productService.saveAllProduct(new ProductAllRequestDTO[]{ valid, invalid });

        assertEquals(1, result.getQuantity());
        assertEquals(1, result.getSavedProducts().size());
    }
}
