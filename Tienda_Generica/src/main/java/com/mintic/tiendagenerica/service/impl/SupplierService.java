package com.mintic.tiendagenerica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.SupplierRequestDTO;
import com.mintic.tiendagenerica.dto.response.SupplierResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.Supplier;
import com.mintic.tiendagenerica.repository.ISupplierRepository;
import com.mintic.tiendagenerica.service.ISupplierService;

@Service
public class SupplierService implements ISupplierService {

	private ISupplierRepository iSupplierRepository;
	private ModelMapper modelMapper;

	public SupplierService(ISupplierRepository iSupplierRepository, ModelMapper modelMapper) {
		this.iSupplierRepository = iSupplierRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<SupplierResponseDTO> getSuppliers() throws TiendaGenericaException {

		List<SupplierResponseDTO> proveedores = iSupplierRepository.findAll().stream()
				.map(user -> this.modelMapper.map(user, SupplierResponseDTO.class)).collect(Collectors.toList());

		if (proveedores.isEmpty())
			throw new TiendaGenericaException("No hay proveedores en el sistema");
		else
			return proveedores;

	}

	@Override
	public SupplierResponseDTO getSupplierByCedula(Long nitProveedor) throws TiendaGenericaException {

		return this.modelMapper.map(
				iSupplierRepository.findSupplierByNitProveedor(nitProveedor)
						.orElseThrow(() -> new TiendaGenericaException("No hay un proveedor con ese Nit")),
				SupplierResponseDTO.class);

	}

	@Override
	public SupplierResponseDTO saveSupplier(SupplierRequestDTO supplier) throws TiendaGenericaException {

		if (iSupplierRepository.existsByNitProveedor(supplier.getNitProveedor())) {

			throw new TiendaGenericaException("Este NIT ya existe en la Base de datos");
		} else {

			return this.modelMapper.map(iSupplierRepository.save(this.modelMapper.map(supplier, Supplier.class)),
					SupplierResponseDTO.class);
		}

	}

	@Override
	public SupplierResponseDTO deleteSupplier(Long nitProveedor) throws TiendaGenericaException {

		Supplier proveedor = iSupplierRepository.findSupplierByNitProveedor(nitProveedor)
				.orElseThrow(() -> new TiendaGenericaException("No hay un proveedor con ese Nit"));

		iSupplierRepository.deleteByNitProveedor(nitProveedor);
		return this.modelMapper.map(proveedor, SupplierResponseDTO.class);

	}

	@Override
	public SupplierResponseDTO updateSupplier(SupplierRequestDTO supplier) throws TiendaGenericaException {

		Optional<Supplier> nuevoProveedor = iSupplierRepository.findSupplierByNitProveedor(supplier.getNitProveedor());

		if (nuevoProveedor.isPresent())
			nuevoProveedor.map(proveedor -> {
				proveedor.setCiudadProveedor(supplier.getCiudadProveedor());
				proveedor.setDireccionProveedor(supplier.getDireccionProveedor());
				proveedor.setNombreProveedor(supplier.getNombreProveedor());
				proveedor.setTelefonoProveedor(supplier.getTelefonoProveedor());
				return proveedor;
			}).get();
		else
			throw new TiendaGenericaException(
					"No existe un proveedor con el Nit: ".concat(supplier.getNitProveedor() + ""));

		return this.modelMapper.map(iSupplierRepository.save(nuevoProveedor.get()), SupplierResponseDTO.class);

	}

	@Override
	public SupplierResponseDTO getSupplierByName(String nombreProveedor) throws TiendaGenericaException {

		return this.modelMapper.map(
				iSupplierRepository.findSupplierByNombreProveedor(nombreProveedor)
						.orElseThrow(() -> new TiendaGenericaException("No hay un proveedor con ese nombre")),
				SupplierResponseDTO.class);

	}

}
