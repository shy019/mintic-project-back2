package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.tiendagenerica.model.Supplier;

@Repository
public interface ISupplierRepository extends MongoRepository<Supplier, Long> {

	Optional<Supplier> findByTelefonoProveedor(String telefonoProveedor);

	Optional<Supplier> findByDireccionProveedor(String direccionProveedor);

	Optional<Supplier> findSupplierByNombreProveedor(String nombreProveedor);

	Optional<Supplier> findSupplierByNitProveedor(Long nitProveedor);

	Boolean existsByNombreProveedor(String nombreProveedor);

	Boolean existsByNitProveedor(Long nitProveedor);

	@Transactional
	void deleteByNitProveedor(Long nitProveedor);

}
