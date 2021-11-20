package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.tiendagenerica.models.jwt.User;

public interface IUserRepository extends MongoRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Transactional
	void deleteByCedula(long cedula);

//	@Modifying
//	@Transactional
//	@Query("update User u set u.name = ?1,u.password = ?2 where u.email = ?3")
//	void setUserInfoById(String name, String password, String email);

	Optional<User> findUserByName(String name);

	Optional<User> findUserByCedula(Long name);
}
