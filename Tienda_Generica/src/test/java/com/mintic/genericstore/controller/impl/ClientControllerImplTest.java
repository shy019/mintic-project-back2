package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.ClientRequestDTO;
import com.mintic.genericstore.dto.response.ClientResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientControllerImplTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientControllerImpl controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClients_returnsList() throws GenericStoreException {
        when(clientService.getClients()).thenReturn(List.of(new ClientResponseDTO()));

        ResponseEntity<List<ClientResponseDTO>> response = controller.getClients();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getClient_returnsClient_whenFound() throws GenericStoreException {
        ClientResponseDTO dto = new ClientResponseDTO();
        when(clientService.getClientById(1L)).thenReturn(dto);

        ResponseEntity<ClientResponseDTO> response = controller.getClient(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void save_returnsCreatedClient() throws GenericStoreException {
        ClientRequestDTO request = new ClientRequestDTO();
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        when(clientService.saveClient(request)).thenReturn(responseDTO);

        ResponseEntity<ClientResponseDTO> response = controller.save(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void update_returnsUpdatedClient() throws GenericStoreException {
        ClientRequestDTO request = new ClientRequestDTO();
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        when(clientService.updateClient(request)).thenReturn(responseDTO);

        ResponseEntity<ClientResponseDTO> response = controller.update(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void delete_returnsDeletedClient() throws GenericStoreException {
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        when(clientService.deleteClient(1L)).thenReturn(responseDTO);

        ResponseEntity<ClientResponseDTO> response = controller.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }
}
