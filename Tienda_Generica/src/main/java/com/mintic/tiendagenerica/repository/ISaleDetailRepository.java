package com.mintic.tiendagenerica.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.tiendagenerica.model.SaleDetail;

@Repository
public interface ISaleDetailRepository extends MongoRepository<SaleDetail, Long> {

	Optional<SaleDetail> findSaleDetailByCantidadProducto(Long cantidadProducto);

	Boolean existsByCodigoVenta(Long codigoVenta);

	Optional<SaleDetail> findSaleDetailByCodigoDetalleVenta(Long codigoDetalleVenta);

	@Transactional
	void deleteByCodigoDetalleVenta(Long codigoVenta);
}
