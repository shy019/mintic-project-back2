package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.UserResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerImpl userController;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers_returnsList() throws GenericStoreException {
        UserResponseDTO user = new UserResponseDTO();
        when(userService.getUsers()).thenReturn(List.of(user));

        ResponseEntity<List<UserResponseDTO>> response = userController.getUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getUser_returnsUser_whenIdExists() throws GenericStoreException {
        UserResponseDTO user = new UserResponseDTO();
        when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<UserResponseDTO> response = userController.getUser(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void save_createsUser() throws GenericStoreException {
        SignupRequestDTO dto = new SignupRequestDTO();
        UserResponseDTO user = new UserResponseDTO();
        when(userService.saveUser(dto)).thenReturn(user);

        ResponseEntity<UserResponseDTO> response = userController.save(dto);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void update_modifiesUser() throws GenericStoreException {
        SignupRequestDTO dto = new SignupRequestDTO();
        UserResponseDTO user = new UserResponseDTO();
        when(userService.updateUser(dto)).thenReturn(user);

        ResponseEntity<UserResponseDTO> response = userController.update(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void delete_removesUser() throws GenericStoreException {
        UserResponseDTO user = new UserResponseDTO();
        when(userService.deleteUser(1L)).thenReturn(user);

        ResponseEntity<UserResponseDTO> response = userController.delete(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
