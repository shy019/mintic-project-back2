package com.mintic.tiendagenerica.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import com.mintic.tiendagenerica.models.jwt.ERole;
import com.mintic.tiendagenerica.models.jwt.Role;
import com.mintic.tiendagenerica.models.jwt.User;
import com.mintic.tiendagenerica.payload.request.LoginRequest;
import com.mintic.tiendagenerica.payload.request.SignupRequest;
import com.mintic.tiendagenerica.payload.response.JwtResponse;
import com.mintic.tiendagenerica.payload.response.MessageResponse;
import com.mintic.tiendagenerica.repository.IRoleRepository;
import com.mintic.tiendagenerica.repository.IUserRepository;
import com.mintic.tiendagenerica.security.jwt.JwtUtils;
import com.mintic.tiendagenerica.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mintic/v1/auth")
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
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getName(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (iUserRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("El usuario ya esta tomado"));
		}

		if (iUserRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("El correo ya esta en uso"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getCedula(), signUpRequest.getName(), signUpRequest.getEmail(),
				signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = iRoleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = iRoleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: ".concat(role).concat(" no encontrado.")));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = iRoleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: ".concat(role).concat(" no encontrado.")));
					roles.add(modRole);

					break;
				default:
					Role userRole = iRoleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: ".concat(role).concat(" no encontrado.")));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		iUserRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Usuario registrado con exito"));
	}
}
