package com.mintic.tiendagenerica.service;

import java.util.List;

import com.mintic.tiendagenerica.dto.request.ClientRequestDTO;
import com.mintic.tiendagenerica.dto.response.ClientResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;

public interface IClientService {
	public List<ClientResponseDTO> getClients() throws TiendaGenericaException;
	
	public ClientResponseDTO getClientByCedula(Long cedulaCliente) throws TiendaGenericaException;
	
	public ClientResponseDTO saveClient(ClientRequestDTO cliente) throws TiendaGenericaException;
	
	public ClientResponseDTO deleteClient(Long cedulaCliente) throws TiendaGenericaException;
	
	public ClientResponseDTO updateClient(ClientRequestDTO cliente) throws TiendaGenericaException;
	
	public ClientResponseDTO getClientByName(String nombreCliente) throws TiendaGenericaException;

}
