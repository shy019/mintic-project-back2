package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByFullName(String name);

	Optional<User> findById(Long id);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	@Transactional
	void deleteById(Long id);

}
