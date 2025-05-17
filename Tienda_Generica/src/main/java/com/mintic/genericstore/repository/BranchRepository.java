package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<Branch, Long> {

}
