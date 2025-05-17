package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.ClientRequestDTO;
import com.mintic.genericstore.dto.response.ClientResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;

import java.util.List;

public interface ClientService {
	List<ClientResponseDTO> getClients() throws GenericStoreException;

	ClientResponseDTO getClientById(Long clientID) throws GenericStoreException;

	ClientResponseDTO saveClient(ClientRequestDTO client) throws GenericStoreException;

	ClientResponseDTO deleteClient(Long clientID) throws GenericStoreException;

	ClientResponseDTO updateClient(ClientRequestDTO client) throws GenericStoreException;

	ClientResponseDTO getClientByName(String clientName) throws GenericStoreException;
}
