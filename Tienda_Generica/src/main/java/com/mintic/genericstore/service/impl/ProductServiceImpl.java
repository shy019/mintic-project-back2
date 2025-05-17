package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.ProductAllRequestDTO;
import com.mintic.genericstore.dto.request.ProductRequestDTO;
import com.mintic.genericstore.dto.response.ProductResponseDTO;
import com.mintic.genericstore.dto.response.ResponseDTO;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Product;
import com.mintic.genericstore.model.Supplier;
import com.mintic.genericstore.repository.ProductRepository;
import com.mintic.genericstore.service.ProductService;
import com.mintic.genericstore.utils.ProductValidator;
import com.mintic.genericstore.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductValidator productValidator;

	@Override
	public List<ProductResponseDTO> getProducts() {
		List<Product> products = productRepository.findAll();

		return getList(products);
	}

	private static List getList(List<Product> products) {
		if (products.isEmpty()) {
			log.warn(NO_PRODUCTS_IN_SYSTEM);
			return Collections.EMPTY_LIST;
		}

		return products.stream()
				.map((product -> MapperUtil.mapToDTO(product, ProductResponseDTO.class)))
				.toList();
	}

	@Override
	public ProductResponseDTO getProductByProductCode(Long productCode) {
		Product product = findProductByIdOrThrow(productCode);
		return MapperUtil.mapToDTO(product, ProductResponseDTO.class);
	}

	@Override
	public ProductResponseDTO getProductByName(String productName) {
		Product product = getProduct(productName);
		return MapperUtil.mapToDTO(product, ProductResponseDTO.class);
	}

	private Product getProduct(String productName) {
        return productRepository.findByProductName(productName)
                .orElseThrow(() -> {
                    log.error(NO_PRODUCT_FOUND_WITH_NAME);
                    return new NotFoundException(NO_PRODUCT_FOUND_WITH_NAME);
                });
	}

	@Override
	public ProductResponseDTO saveProduct(ProductRequestDTO dto) {
		productValidator.validateProductDoesNotExist(dto.getProductId());
		Supplier supplier = productValidator.validateSupplierExists(dto.getSupplier().getSupplierId());

		Product product = MapperUtil.mapToDTO(dto, Product.class);
		product.setSupplier(supplier);

		return MapperUtil.mapToDTO(productRepository.save(product), ProductResponseDTO.class);
	}

	@Override
	public ProductResponseDTO updateProduct(ProductRequestDTO dto) {
		Product product = findProductByIdOrThrow(dto.getProductId());
		Supplier supplier = productValidator.validateSupplierExists(dto.getSupplier().getSupplierId());

		updateProductFromDTO(product, dto, supplier);

		return MapperUtil.mapToDTO(productRepository.save(product), ProductResponseDTO.class);
	}

	@Override
	public ProductResponseDTO deleteProduct(Long productCode) {
		Product product = findProductByIdOrThrow(productCode);
		productRepository.deleteByProductId(productCode);
		return MapperUtil.mapToDTO(product, ProductResponseDTO.class);
	}

	@Override
	public ResponseDTO saveAllProduct(ProductAllRequestDTO[] productDTOs) {
		int savedCount = 0;
		List<ProductResponseDTO> saved = new ArrayList<>();
		List<ProductResponseDTO> failed = new ArrayList<>();

		savedCount = getSavedCount(productDTOs, failed, saved, savedCount);

		return new ResponseDTO(
				savedCount,
				String.format(SAVED_PRODUCTS_MESSAGE, savedCount, productDTOs.length - savedCount),
				saved,
				failed
		);
	}

	private int getSavedCount(ProductAllRequestDTO[] productDTOs, List<ProductResponseDTO> failed, List<ProductResponseDTO> saved, int savedCount) {
		for (ProductAllRequestDTO dto : productDTOs) {
			boolean exists = productRepository.findByProductId(dto.getProductCode()).isPresent();
			Optional<Supplier> supplierOpt = productValidator.validateSupplierOptional(dto.getSupplierId());

			if (exists || supplierOpt.isEmpty()) {
				failed.add(MapperUtil.mapToDTO(dto, ProductResponseDTO.class));
				continue;
			}

			Product product = fromAllRequestDTO(dto, supplierOpt.get());
			saved.add(MapperUtil.mapToDTO(productRepository.save(product), ProductResponseDTO.class));
			savedCount++;
		}
		return savedCount;
	}

	// Private helpers
	private Product findProductByIdOrThrow(Long productCode) {
		return productRepository.findByProductId(productCode)
				.orElseThrow(() -> {
					log.error(NO_PRODUCT_FOUND_WITH_CODE_EXT + productCode);
					return new NotFoundException(NO_PRODUCT_FOUND_WITH_CODE_EXT + productCode);
				});
	}

	private void updateProductFromDTO(Product product, ProductRequestDTO dto, Supplier supplier) {
		product.setProductName(dto.getProductName());
		product.setSupplier(supplier);
		product.setPurchasePrice(dto.getPurchasePrice());
		product.setSalePrice(dto.getSalePrice());
	}

	private Product fromAllRequestDTO(ProductAllRequestDTO dto, Supplier supplier) {
		Product product = new Product();
		product.setProductId(dto.getProductCode());
		product.setProductName(dto.getProductName());
		product.setPurchasePrice(dto.getPurchasePrice());
		product.setSalePrice(dto.getSalePrice());
		product.setPurchaseTax(dto.getPurchaseTAX());
		product.setSupplier(supplier);
		return product;
	}
}
