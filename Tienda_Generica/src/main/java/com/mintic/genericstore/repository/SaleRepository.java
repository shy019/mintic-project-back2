package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepository extends MongoRepository<Sale, Long> {

	Optional<Sale> findBySaleId(Long saleCode);

	void deleteBySaleId(Long saleCode);
}
