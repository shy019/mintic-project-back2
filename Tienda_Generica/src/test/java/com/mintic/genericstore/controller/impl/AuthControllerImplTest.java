package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.LoginRequestDTO;
import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.JwtResponse;
import com.mintic.genericstore.dto.response.MessageResponse;
import com.mintic.genericstore.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerImplTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthControllerImpl authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticateUser_shouldReturnJwtResponse() {
        LoginRequestDTO loginRequest = new LoginRequestDTO("user", "pass");
        JwtResponse jwtResponse = new JwtResponse("token", 123L, "user", "User Name", "user@example.com", List.of("ROLE_USER"));
        when(authService.authenticateUser(loginRequest)).thenReturn(ResponseEntity.ok(jwtResponse));

        ResponseEntity<JwtResponse> response = authController.authenticateUser(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(jwtResponse, response.getBody());
        verify(authService, times(1)).authenticateUser(loginRequest);
    }

    @Test
    void registerUser_shouldReturnMessageResponse() {
        SignupRequestDTO signupRequest = new SignupRequestDTO();
        signupRequest.setUsername("user");
        signupRequest.setEmail("user@example.com");
        signupRequest.setPassword("password");
        signupRequest.setIdNumber(123456L);
        signupRequest.setFullName("User Name");
        signupRequest.setRoles(Set.of("user"));

        MessageResponse messageResponse = new MessageResponse("User registered successfully!");
        when(authService.registerUser(signupRequest)).thenReturn(ResponseEntity.ok(messageResponse));

        ResponseEntity<MessageResponse> response = authController.registerUser(signupRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(messageResponse, response.getBody());
        verify(authService, times(1)).registerUser(signupRequest);
    }
}