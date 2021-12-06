package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mintic.tiendagenerica.model.jwt.ERole;
import com.mintic.tiendagenerica.model.jwt.Role;

@Repository
public interface IRoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(ERole name);

}
