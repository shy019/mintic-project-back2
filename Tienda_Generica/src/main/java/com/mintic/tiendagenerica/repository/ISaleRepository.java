package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.tiendagenerica.model.Sale;

@Repository
public interface ISaleRepository extends MongoRepository<Sale, Long>{
	
	Optional<Sale> findSaleByCodigoVenta(Long codigoVenta);
	
	Boolean existsByCodigoVenta(Long codigoVenta);
	
	Optional<Sale> findSaleByCedulaCliente(Long cedulaCliente);
	
	@Transactional
	void deleteByCodigoVenta(Long codigoVenta);
}
