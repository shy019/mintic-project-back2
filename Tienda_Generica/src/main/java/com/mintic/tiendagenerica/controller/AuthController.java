package com.mintic.tiendagenerica.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.tiendagenerica.payload.request.LoginRequestDTO;
import com.mintic.tiendagenerica.payload.response.JwtResponse;
import com.mintic.tiendagenerica.repository.IRoleRepository;
import com.mintic.tiendagenerica.repository.IUserRepository;
import com.mintic.tiendagenerica.security.jwt.JwtUtils;
import com.mintic.tiendagenerica.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tiendagenerica/auth")
public class AuthController {

	AuthenticationManager authenticationManager;

	IUserRepository iUserRepository;

	IRoleRepository iRoleRepository;

	PasswordEncoder encoder;

	JwtUtils jwtUtils;

	public AuthController(AuthenticationManager authenticationManager, IUserRepository iUserRepository,
			IRoleRepository iRoleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
		super();
		this.authenticationManager = authenticationManager;
		this.iUserRepository = iUserRepository;
		this.iRoleRepository = iRoleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getCedula(), userDetails.getUsername(),
				userDetails.getName(), userDetails.getEmail(), roles));
	}

}
