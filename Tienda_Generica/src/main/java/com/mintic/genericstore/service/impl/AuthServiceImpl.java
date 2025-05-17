package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.LoginRequestDTO;
import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.JwtResponse;
import com.mintic.genericstore.dto.response.MessageResponse;
import com.mintic.genericstore.model.ERole;
import com.mintic.genericstore.model.Role;
import com.mintic.genericstore.model.User;
import com.mintic.genericstore.model.UserDetails;
import com.mintic.genericstore.repository.RoleRepository;
import com.mintic.genericstore.repository.UserRepository;
import com.mintic.genericstore.config.JwtUtils;
import com.mintic.genericstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.mintic.genericstore.utils.constants.ControllerConstants.*;
import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;

	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager,
						   UserRepository userRepository,
						   RoleRepository roleRepository,
						   PasswordEncoder passwordEncoder,
						   JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public ResponseEntity<JwtResponse> authenticateUser(LoginRequestDTO loginRequestDTO) {
		Authentication authentication = getAuthentication(loginRequestDTO);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtResponse jwtResponse = getJwtResponse(userDetails, jwt);
		return ResponseEntity.ok(jwtResponse);
	}

	private Authentication getAuthentication(LoginRequestDTO loginRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDTO.getUsername(),
						loginRequestDTO.getPassword()
				)
		);
		return authentication;
	}

	private static JwtResponse getJwtResponse(UserDetails userDetails, String jwt) {
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		JwtResponse jwtResponse = new JwtResponse(
				jwt,
				userDetails.getIdNumber(),
				userDetails.getUsername(),
				userDetails.getFullName(),
				userDetails.getEmail(),
				roles
		);
		return jwtResponse;
	}

	@Override
	public ResponseEntity<MessageResponse> registerUser(SignupRequestDTO signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse(USERNAME_IS_TAKEN));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse(EMAIL_IN_USE));
		}
		User user = getUser(signUpRequest);
		Set<Role> roles = new HashSet<>();
		processRole(signUpRequest, roles);
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse(USER_REGISTERED));
	}

	private User getUser(SignupRequestDTO signUpRequest) {
        return User.builder()
                .idNumber(signUpRequest.getIdNumber())
                .fullName(signUpRequest.getFullName())
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
	}

	private void processRole(SignupRequestDTO signUpRequest, Set<Role> roles) {
		if (signUpRequest.getRoles() == null || signUpRequest.getRoles().isEmpty()) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException(DEFAULT_ROLE_NOT_FOUND));
			roles.add(userRole);
		} else {
			for (String roleStr : signUpRequest.getRoles()) {
				ERole roleEnum = switch (roleStr.toLowerCase()) {
					case "admin" -> ERole.ROLE_ADMIN;
					case "mod" -> ERole.ROLE_MODERATOR;
					default -> ERole.ROLE_USER;
				};
				Role role = roleRepository.findByName(roleEnum)
						.orElseThrow(() -> new RuntimeException(String.format(ROLE_NOT_FOUND, roleStr)));
				roles.add(role);
			}
		}
	}
}
