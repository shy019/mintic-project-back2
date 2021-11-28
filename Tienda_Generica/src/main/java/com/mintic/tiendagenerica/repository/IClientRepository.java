package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.tiendagenerica.model.Client;

@Repository
public interface IClientRepository extends MongoRepository<Client, Long> {
	
	Optional<Client> findByTelefonoCliente(String telefonoCliente);

	Optional<Client> findByDireccionCliente(String direccionCliente);

	Optional<Client> findClientByNombreCliente(String nombreCliente);

	Optional<Client> findClientByCedulaCliente(Long cedulaCliente);

	Boolean existsByNombreCliente(String nombreCliente);

	Boolean existsByCedulaCliente(Long cedulaCliente);

	@Transactional
	void deleteByCedulaCliente(Long cedulaCliente);
}
