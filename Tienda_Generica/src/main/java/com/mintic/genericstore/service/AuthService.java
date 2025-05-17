package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.LoginRequestDTO;
import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.JwtResponse;
import com.mintic.genericstore.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

	ResponseEntity<JwtResponse> authenticateUser(LoginRequestDTO loginRequestDTO);

	ResponseEntity<MessageResponse> registerUser(SignupRequestDTO signUpRequest);
}