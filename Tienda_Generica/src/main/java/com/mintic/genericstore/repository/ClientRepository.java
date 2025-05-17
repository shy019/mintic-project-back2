package com.mintic.genericstore.repository;

import com.mintic.genericstore.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, Long> {

	Optional<Client> findByClientName(String clientName);

	Optional<Client> findByClientId(Long clientId);

	boolean existsByClientId(Long clientId);

	@Transactional
	void deleteByClientId(Long clientId);
}
