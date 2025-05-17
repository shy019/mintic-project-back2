package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.ERole;
import com.mintic.genericstore.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(ERole name);

}
