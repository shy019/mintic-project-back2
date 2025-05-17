package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

	Optional<Product> findByProductId(Long productCode);

	Optional<Product> findByProductName(String productName);


	@Transactional
	void deleteByProductId(Long productCode);
}
