package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.ClientRequestDTO;
import com.mintic.genericstore.dto.response.ClientResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.ClientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class ClientControllerImpl implements com.mintic.genericstore.controller.ClientController {

	@Qualifier("ClientServiceImpl")
	private final ClientService clientService;

	public ClientControllerImpl(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public ResponseEntity<List<ClientResponseDTO>> getClients() throws GenericStoreException {
		return ResponseEntity.ok(clientService.getClients());
	}

	@Override
	public ResponseEntity<ClientResponseDTO> getClient(Long idCliente) throws GenericStoreException {
		return ResponseEntity.ok(clientService.getClientById(idCliente));
	}

	@Override
	public ResponseEntity<ClientResponseDTO> save(ClientRequestDTO client) throws GenericStoreException {
		return ResponseEntity.ok(clientService.saveClient(client));
	}

	@Override
	public ResponseEntity<ClientResponseDTO> update(ClientRequestDTO client) throws GenericStoreException {
		return ResponseEntity.ok(clientService.updateClient(client));
	}

	@Override
	public ResponseEntity<ClientResponseDTO> delete(Long idCliente) throws GenericStoreException {
		return ResponseEntity.ok(clientService.deleteClient(idCliente));
	}
}
