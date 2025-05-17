package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.SaleDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleDetailRepository extends MongoRepository<SaleDetail, Long> {

	Optional<SaleDetail> findBySaleDetailId(Long saleDetailCode);

	void deleteBySaleDetailId(Long saleCode);
}
