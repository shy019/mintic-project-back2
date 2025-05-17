package com.mintic.genericstore.utils;

import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Supplier;
import com.mintic.genericstore.repository.ProductRepository;
import com.mintic.genericstore.repository.SupplierRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.mintic.genericstore.utils.constants.ServiceConstants.PRODUCT_EXIST_MESSAGE;
import static com.mintic.genericstore.utils.constants.ServiceConstants.SUPPLIER_NOT_FOUND;

@Component
public class ProductValidator {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductValidator(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public void validateProductDoesNotExist(Long productCode) {
        if (productRepository.findByProductId(productCode).isPresent()) {
            throw new NotFoundException(PRODUCT_EXIST_MESSAGE + productCode);
        }
    }

    public Supplier validateSupplierExists(Long supplierId) {
        return supplierRepository.findBySupplierId(supplierId)
                .orElseThrow(() -> new NotFoundException(SUPPLIER_NOT_FOUND + supplierId));
    }

    public Optional<Supplier> validateSupplierOptional(Long supplierId) {
        return supplierRepository.findBySupplierId(supplierId);
    }
}
