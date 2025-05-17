package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.ClientRequestDTO;
import com.mintic.genericstore.dto.response.ClientResponseDTO;
import com.mintic.genericstore.model.Client;
import com.mintic.genericstore.repository.ClientRepository;
import com.mintic.genericstore.service.ClientService;
import com.mintic.genericstore.utils.ClientValidator;
import com.mintic.genericstore.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service("ClientServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final ClientValidator clientValidator;

	@Override
	public List<ClientResponseDTO> getClients() {
		log.info(FETCHING_ALL_CLIENTS_CLIENT);
		List<Client> clients = clientRepository.findAll();

		List<ClientResponseDTO> emptyList = getClientResponseDTOS(clients);
		if (emptyList != null) return emptyList;

		return clients.stream()
				.map(this::mapToResponseDto)
				.collect(Collectors.toList());
	}

	private static List<ClientResponseDTO> getClientResponseDTOS(List<Client> clients) {
		if (clients.isEmpty()) {
			log.warn(NO_CLIENTS_IN_SYSTEM);
			return Collections.emptyList();
		}
		return null;
	}

	@Override
	public ClientResponseDTO getClientById(Long clientId) {
		log.info(FETCHING_CLIENT_BY_ID_CLIENT, clientId);
		Client client = clientValidator.validateClientExistsById(clientId);
		return mapToResponseDto(client);
	}

	@Override
	@Transactional
	public ClientResponseDTO saveClient(ClientRequestDTO clientRequest) {
		log.info(SAVING_NEW_CLIENT_CLIENT, clientRequest.getClientId());
		clientValidator.ensureClientDoesNotExist(clientRequest.getClientId());

		Client client = mapToEntity(clientRequest);
		Client savedClient = clientRepository.save(client);
		log.info(CLIENT_SAVED_SUCCESSFULLY_CLIENT, savedClient.getClientId());
		return mapToResponseDto(savedClient);
	}

	@Override
	@Transactional
	public ClientResponseDTO deleteClient(Long clientId) {

		log.info(DELETING_CLIENT_CLIENT, clientId);
		Client client = clientValidator.validateClientExistsById(clientId);
		clientRepository.deleteByClientId(clientId);

		log.info(CLIENT_DELETED_SUCCESSFULLY_CLIENT, clientId);
		return mapToResponseDto(client);
	}

	@Override
	@Transactional
	public ClientResponseDTO updateClient(ClientRequestDTO clientRequest) {

		Long clientId = clientRequest.getClientId();
		log.info(UPDATING_CLIENT_CLIENT, clientId);
		Client updatedClient = getClient(clientRequest, clientId);

		log.info(CLIENT_UPDATED_SUCCESSFULLY_CLIENT, updatedClient.getClientId());
		return mapToResponseDto(updatedClient);
	}

	private Client getClient(ClientRequestDTO clientRequest, Long clientId) {

		Client existingClient = clientValidator.validateClientExistsById(clientId);

		existingClient.setClientName(clientRequest.getClientName());
		existingClient.setClientEmail(clientRequest.getClientEmail());
		existingClient.setClientAddress(clientRequest.getClientAddress());
		existingClient.setClientPhoneNumber(clientRequest.getClientPhoneNumber());
        return clientRepository.save(existingClient);
	}

	@Override
	public ClientResponseDTO getClientByName(String clientName) {

		log.info(FETCHING_CLIENT_BY_NAME_CLIENT, clientName);
		Client client = clientValidator.validateClientExistsByName(clientName);
		return mapToResponseDto(client);
	}

	private ClientResponseDTO mapToResponseDto(Client client) {

		return MapperUtil.mapToDTO(client, ClientResponseDTO.class);
	}

	private Client mapToEntity(ClientRequestDTO requestDTO) {

		return MapperUtil.mapToDTO(requestDTO, Client.class);
	}
}
