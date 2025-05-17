
package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.ClientRequestDTO;
import com.mintic.genericstore.dto.response.ClientResponseDTO;
import com.mintic.genericstore.model.Client;
import com.mintic.genericstore.repository.ClientRepository;
import com.mintic.genericstore.utils.ClientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientValidator clientValidator;

    @InjectMocks
    private ClientServiceImpl clientService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClients_returnsList_whenNotEmpty() {
        Client client = new Client();
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<ClientResponseDTO> result = clientService.getClients();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getClients_returnsEmpty_whenNoClients() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        List<ClientResponseDTO> result = clientService.getClients();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getClientById_returnsDTO_whenClientExists() {
        Client client = new Client();
        when(clientValidator.validateClientExistsById(1L)).thenReturn(client);

        ClientResponseDTO result = clientService.getClientById(1L);

        assertNotNull(result);
    }

    @Test
    void saveClient_savesAndReturnsDTO() {
        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setClientId(1L);
        Client client = new Client();
        client.setClientId(1L);

        when(clientRepository.save(any())).thenReturn(client);

        ClientResponseDTO result = clientService.saveClient(dto);

        assertNotNull(result);
        verify(clientValidator).ensureClientDoesNotExist(1L);
        verify(clientRepository).save(any());
    }

    @Test
    void deleteClient_deletesAndReturnsDTO() {
        Client client = new Client();
        when(clientValidator.validateClientExistsById(1L)).thenReturn(client);

        ClientResponseDTO result = clientService.deleteClient(1L);

        assertNotNull(result);
        verify(clientRepository).deleteByClientId(1L);
    }

    @Test
    void updateClient_updatesAndReturnsDTO() {
        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setClientId(1L);
        dto.setClientName("Updated");

        Client existingClient = new Client();
        when(clientValidator.validateClientExistsById(1L)).thenReturn(existingClient);
        when(clientRepository.save(any())).thenReturn(existingClient);

        ClientResponseDTO result = clientService.updateClient(dto);

        assertNotNull(result);
        verify(clientRepository).save(any());
    }

    @Test
    void getClientByName_returnsDTO_whenFound() {
        Client client = new Client();
        when(clientValidator.validateClientExistsByName("Juan")).thenReturn(client);

        ClientResponseDTO result = clientService.getClientByName("Juan");

        assertNotNull(result);
    }
}
