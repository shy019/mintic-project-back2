package com.mintic.tiendagenerica.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.SaleRequestDTO;
import com.mintic.tiendagenerica.dto.response.SaleResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.Sale;
import com.mintic.tiendagenerica.model.jwt.User;
import com.mintic.tiendagenerica.repository.ISaleRepository;
import com.mintic.tiendagenerica.repository.IUserRepository;
import com.mintic.tiendagenerica.service.ISaleService;

@Service
public class SaleService implements ISaleService {

	private ISaleRepository iSaleRepository;
	private IUserRepository iUserRepository;
	private ModelMapper modelMapper;

	public SaleService(ISaleRepository iSaleRepository, IUserRepository iUserRepository, ModelMapper modelMapper) {
		this.iSaleRepository = iSaleRepository;
		this.iUserRepository = iUserRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<SaleResponseDTO> getSales() throws TiendaGenericaException {
		List<SaleResponseDTO> ventas = iSaleRepository.findAll().stream()
				.map(user -> this.modelMapper.map(user, SaleResponseDTO.class)).collect(Collectors.toList());

		if (ventas.isEmpty())
			throw new TiendaGenericaException("No hay ventas en el sistema");
		else
			return ventas;
	}

	@Override
	public SaleResponseDTO getSaleByCodigoVenta(Long codigoVenta) throws TiendaGenericaException {
		return this.modelMapper.map(iSaleRepository.findSaleByCodigoVenta(codigoVenta).orElseThrow(
				() -> new TiendaGenericaException("No hay una venta con ese código")), SaleResponseDTO.class);
	}

	@Override
	public SaleResponseDTO saveSale(SaleRequestDTO sale) throws TiendaGenericaException {
		User user = iUserRepository.findById(sale.getCedulaUsuario())
				.orElseThrow(() -> new TiendaGenericaException("No existe un usuario con ese id"));

		Sale newSale = this.modelMapper.map(sale, Sale.class);
		newSale.setCedula(user);
		newSale.setCodigoVenta(iSaleRepository.count() + 1);
		return this.modelMapper.map(iSaleRepository.save(newSale), SaleResponseDTO.class);
	}

	@Override
	public SaleResponseDTO deleteSale(Long codigoVenta) throws TiendaGenericaException {
		Sale venta = iSaleRepository.findSaleByCodigoVenta(codigoVenta)
				.orElseThrow(() -> new TiendaGenericaException("No hay una venta con este código"));

		iSaleRepository.deleteByCodigoVenta(codigoVenta);
		return this.modelMapper.map(venta, SaleResponseDTO.class);
	}

	@Override
	public SaleResponseDTO updateSale(SaleRequestDTO sale) throws TiendaGenericaException {

		User user = iUserRepository.findById(sale.getCedulaUsuario())
				.orElseThrow(() -> new TiendaGenericaException("No existe un usuario con ese id"));

		Sale newSale = this.modelMapper.map(sale, Sale.class);
		newSale.setCedula(user);
		newSale.setCodigoVenta(sale.getCodigoVenta());
		return this.modelMapper.map(iSaleRepository.save(newSale), SaleResponseDTO.class);

	}

}