package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, Long> {

	Optional<Supplier> findBySupplierName(String supplierName);

	Optional<Supplier> findBySupplierId(Long SupplierId);

	boolean existsBySupplierId(Long SupplierId);

	@Transactional
	void deleteBySupplierId(Long SupplierId);

}
