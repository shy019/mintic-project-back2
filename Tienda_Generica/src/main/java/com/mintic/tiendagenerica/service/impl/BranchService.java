package com.mintic.tiendagenerica.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.BranchRequestDTO;
import com.mintic.tiendagenerica.dto.response.BranchResponseDTO;
import com.mintic.tiendagenerica.dto.response.SaleByBranchResponseDTO;
import com.mintic.tiendagenerica.dto.response.SaleDetailResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.Branch;
import com.mintic.tiendagenerica.repository.IBranchRepository;
import com.mintic.tiendagenerica.repository.ISaleDetailRepository;
import com.mintic.tiendagenerica.service.IBranchService;

@Service
public class BranchService implements IBranchService {

	private IBranchRepository iBranchRepository;
	private ModelMapper modelMapper;
	private ISaleDetailRepository iSaleDetailRepository;

	public BranchService(IBranchRepository iBranchRepository, ModelMapper modelMapper,
			ISaleDetailRepository iSaleDetailRepository) {
		this.iBranchRepository = iBranchRepository;
		this.modelMapper = modelMapper;
		this.iSaleDetailRepository = iSaleDetailRepository;
	}

	@Override
	public BranchResponseDTO saveBranch(BranchRequestDTO branch) throws TiendaGenericaException {
		return this.modelMapper.map(iBranchRepository.save(this.modelMapper.map(branch, Branch.class)),
				BranchResponseDTO.class);
	}

	@Override
	public List<SaleByBranchResponseDTO> getSalesByBranch(BranchRequestDTO branch) throws TiendaGenericaException {

		SaleByBranchResponseDTO listaRespuesta = new SaleByBranchResponseDTO();

		List<SaleDetailResponseDTO> dataDb = iSaleDetailRepository.findAll().stream().filter(data -> {
			return data.getSucursal().getId() == this.modelMapper.map(branch, Branch.class).getId();
		}).map(data -> this.modelMapper.map(data, SaleDetailResponseDTO.class)).collect(Collectors.toList());

		Double ivaTotalVenta = dataDb.stream().mapToDouble(dato -> dato.getCodigoVenta().getIvaVenta())
				.reduce((acumulador, numero) -> {
					return acumulador + numero;
				}).getAsDouble();

		Long cantidadProductosVendidos = dataDb.stream().mapToLong(dato -> dato.getCantidadProducto())
				.reduce((acumulador, numero) -> {
					return acumulador + numero;
				}).getAsLong();

		Double valorTotalVenta = dataDb.stream().mapToDouble(dato -> dato.getValorTotal())
				.reduce((acumulador, numero) -> {
					return acumulador + numero;
				}).getAsDouble();

		Double valorTotalMasIvaVenta = dataDb.stream().mapToDouble(dato -> dato.getValorVenta())
				.reduce((acumulador, numero) -> {
					return acumulador + numero;
				}).getAsDouble();

		listaRespuesta.setIvaTotalVenta(ivaTotalVenta);
		listaRespuesta.setCantidadProductosVendidos(cantidadProductosVendidos);
		listaRespuesta.setSucursal(branch.getNombreSucursal());
		listaRespuesta.setValorTotalVenta(valorTotalVenta);
		listaRespuesta.setValorTotalMasIvaVenta(valorTotalMasIvaVenta);

		List<SaleByBranchResponseDTO> res = new ArrayList<SaleByBranchResponseDTO>();
		res.add(listaRespuesta);
		
		return res;

	}

}