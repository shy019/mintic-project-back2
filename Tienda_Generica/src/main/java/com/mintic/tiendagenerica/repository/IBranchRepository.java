package com.mintic.tiendagenerica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mintic.tiendagenerica.model.Branch;

public interface IBranchRepository extends MongoRepository<Branch, Long> {

}
