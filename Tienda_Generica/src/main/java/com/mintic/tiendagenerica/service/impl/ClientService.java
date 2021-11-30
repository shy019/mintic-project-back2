package com.mintic.tiendagenerica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.ClientRequestDTO;
import com.mintic.tiendagenerica.dto.response.ClientResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.Client;
import com.mintic.tiendagenerica.repository.IClientRepository;
import com.mintic.tiendagenerica.service.IClientService;

@Service
public class ClientService implements IClientService {
	
	private IClientRepository iClientRepository;
	private ModelMapper modelMapper;

	public ClientService(IClientRepository iClientRepository, ModelMapper modelMapper) {
		this.iClientRepository = iClientRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ClientResponseDTO> getClients() throws TiendaGenericaException {
		List<ClientResponseDTO> clientes = iClientRepository.findAll().stream()
				.map(user -> this.modelMapper.map(user, ClientResponseDTO.class)).collect(Collectors.toList());

		if (clientes.isEmpty())
			throw new TiendaGenericaException("No hay clientes en el sistema");
		else
			return clientes;
	}

	@Override
	public ClientResponseDTO getClientByCedula(Long cedulaCliente) throws TiendaGenericaException {
		return this.modelMapper.map(
				iClientRepository.findClientByCedulaCliente(cedulaCliente)
						.orElseThrow(() -> new TiendaGenericaException("No hay un cliente con esa cédula")),
				ClientResponseDTO.class);
	}

	@Override
	public ClientResponseDTO saveClient(ClientRequestDTO cliente) throws TiendaGenericaException {
		
		
		if (iClientRepository.existsByCedulaCliente(cliente.getCedulaCliente())) {

			throw new TiendaGenericaException("Esta cédula ya existe en la Base de datos");
		} else {

			return this.modelMapper.map(iClientRepository.save(this.modelMapper.map(cliente, Client.class)),
					ClientResponseDTO.class);
		}
	}

	@Override
	public ClientResponseDTO deleteClient(Long cedulaCliente) throws TiendaGenericaException {
		Client cliente = iClientRepository.findClientByCedulaCliente(cedulaCliente)
				.orElseThrow(() -> new TiendaGenericaException("No hay un cliente con esa cédula"));

		iClientRepository.deleteByCedulaCliente(cedulaCliente);
		return this.modelMapper.map(cliente, ClientResponseDTO.class);
	}

	@Override
	public ClientResponseDTO updateClient(ClientRequestDTO client) throws TiendaGenericaException {
		Optional<Client> nuevoCliente = iClientRepository.findClientByCedulaCliente(client.getCedulaCliente());

		if (nuevoCliente.isPresent())
			nuevoCliente.map(cliente -> {
				cliente.setEmailCliente(client.getEmailCliente());
				cliente.setDireccionCliente(client.getDireccionCliente());
				cliente.setNombreCliente(client.getNombreCliente());
				cliente.setTelefonoCliente(client.getTelefonoCliente());
				return cliente;
			}).get();
		else
			throw new TiendaGenericaException(
					"No existe un cliente con la cédula: ".concat(client.getCedulaCliente() + ""));

		return this.modelMapper.map(iClientRepository.save(nuevoCliente.get()), ClientResponseDTO.class);
	}

	@Override
	public ClientResponseDTO getClientByName(String nombreCliente) throws TiendaGenericaException {
		
		return this.modelMapper.map(
				iClientRepository.findClientByNombreCliente(nombreCliente)
						.orElseThrow(() -> new TiendaGenericaException("No hay un cliente con ese nombre")),
				ClientResponseDTO.class);
	}

}
