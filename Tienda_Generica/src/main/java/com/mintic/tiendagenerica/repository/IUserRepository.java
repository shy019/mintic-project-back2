package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.tiendagenerica.model.jwt.User;

@Repository
public interface IUserRepository extends MongoRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findUserByName(String name);

	Optional<User> findUserByCedula(Long cedula);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Transactional
	void deleteByCedula(Long cedula);

}
