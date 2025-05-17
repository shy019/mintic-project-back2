package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.controller.AuthController;
import com.mintic.genericstore.dto.request.LoginRequestDTO;
import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.JwtResponse;
import com.mintic.genericstore.dto.response.MessageResponse;
import com.mintic.genericstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class AuthControllerImpl implements AuthController {

	private final AuthService authService;

	@Autowired
	public AuthControllerImpl(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public ResponseEntity<JwtResponse> authenticateUser(LoginRequestDTO loginRequestDTO) {
		return authService.authenticateUser(loginRequestDTO);
	}

	@Override
	public ResponseEntity<MessageResponse> registerUser(SignupRequestDTO signUpRequest) {
		return authService.registerUser(signUpRequest);
	}
}
