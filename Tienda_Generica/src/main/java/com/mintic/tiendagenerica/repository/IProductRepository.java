package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.tiendagenerica.model.Product;

@Repository
public interface IProductRepository extends MongoRepository<Product, Long> {

	Optional<Product> findProductByCodigoProducto(Long codigoProducto);
	
	Boolean existsByNombreProducto(String nombreProducto);
	
	Boolean existsByCodigoProducto(Long codigoProducto);
	
	Optional<Product> findProductByNombreProducto (String nombreProducto);
	
	Optional<Product> findProductByNitProveedor(Long nitProveedor);

	@Transactional
	void deleteByCodigoProducto(Long codigoProducto);
	
}

