package com.mintic.tiendagenerica.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.SaleDetailRequestDTO;
import com.mintic.tiendagenerica.dto.response.SaleDetailResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.SaleDetail;
import com.mintic.tiendagenerica.repository.IBranchRepository;
import com.mintic.tiendagenerica.repository.ISaleDetailRepository;
import com.mintic.tiendagenerica.service.ISaleDetailService;

@Service
public class SaleDetailService implements ISaleDetailService {

	private ISaleDetailRepository iSaleDetailRepository;
	private IBranchRepository iBranchRepository;
	private ModelMapper modelMapper;

	public SaleDetailService(ISaleDetailRepository iSaleDetailRepository, IBranchRepository iBranchRepository,
			ModelMapper modelMapper) {
		super();
		this.iSaleDetailRepository = iSaleDetailRepository;
		this.iBranchRepository = iBranchRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<SaleDetailResponseDTO> getSaleDetails() throws TiendaGenericaException {

		List<SaleDetailResponseDTO> detalleVentas = iSaleDetailRepository.findAll().stream()
				.map(user -> this.modelMapper.map(user, SaleDetailResponseDTO.class)).collect(Collectors.toList());

		if (detalleVentas.isEmpty())
			throw new TiendaGenericaException("No hay detalle de ventas en el sistema");
		else
			return detalleVentas;

	}

	@Override
	public SaleDetailResponseDTO getSaleDetailByCodigoVenta(Long codigoVenta) throws TiendaGenericaException {

		return this.modelMapper.map(
				iSaleDetailRepository.findSaleDetailByCodigoDetalleVenta(codigoVenta)
						.orElseThrow(() -> new TiendaGenericaException("No hay un detalle venta con ese código")),
				SaleDetailResponseDTO.class);

	}

	@Override
	public SaleDetailResponseDTO deleteSaleDetail(Long codigoVenta) throws TiendaGenericaException {

		SaleDetail venta = iSaleDetailRepository.findSaleDetailByCodigoDetalleVenta(codigoVenta)
				.orElseThrow(() -> new TiendaGenericaException("No hay una venta con este código"));

		iSaleDetailRepository.deleteByCodigoDetalleVenta(codigoVenta);
		return this.modelMapper.map(venta, SaleDetailResponseDTO.class);

	}

	@Override
	public SaleDetailResponseDTO updateSaleDetail(SaleDetailRequestDTO saleDetail) throws TiendaGenericaException {

		saleDetail.setCodigoDetalleVenta(iSaleDetailRepository.count() + 1);
		return this.modelMapper.map(iSaleDetailRepository.save(this.modelMapper.map(saleDetail, SaleDetail.class)),
				SaleDetailResponseDTO.class);

	}

	@Override
	public String saveAllSaleDetail(SaleDetailRequestDTO[] saleDetail) throws TiendaGenericaException {

		try {
			verifyIfBranchExists(saleDetail);
			Arrays.asList(saleDetail).stream().map(detail -> {
				detail.setCodigoDetalleVenta(iSaleDetailRepository.count() + 1);
				return this.modelMapper.map(detail, SaleDetail.class);
			}).forEach(detailModel -> {
				detailModel.setSucursal(iBranchRepository.findById(detailModel.getSucursal().getId()).get());
				iSaleDetailRepository.save(detailModel);
			});
			return "Venta realizada con exito!";
		} catch (Exception e) {
			throw new TiendaGenericaException("No se pudo guardar el detalle de la venta");
		}

	}

	private void verifyIfBranchExists(SaleDetailRequestDTO[] saleDetail) throws TiendaGenericaException {

		Arrays.asList(saleDetail).stream().forEach(detail -> {
			try {
				iBranchRepository.findById(detail.getSucursal().getId())
						.orElseThrow(() -> new TiendaGenericaException("No hay una venta con este código"));
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

	}

}