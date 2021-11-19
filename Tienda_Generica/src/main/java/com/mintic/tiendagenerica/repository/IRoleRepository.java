package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mintic.tiendagenerica.models.jwt.ERole;
import com.mintic.tiendagenerica.models.jwt.Role;

public interface IRoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
