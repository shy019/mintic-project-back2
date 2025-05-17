package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.UserResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.ERole;
import com.mintic.genericstore.model.Role;
import com.mintic.genericstore.model.User;
import com.mintic.genericstore.repository.RoleRepository;
import com.mintic.genericstore.repository.UserRepository;
import com.mintic.genericstore.service.UserService;
import com.mintic.genericstore.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Override
	@Cacheable(value = "users")
	public List<UserResponseDTO> getUsers() {
		List<User> users = userRepository.findAll();

		if (users.isEmpty()) {
			log.warn(NO_USERS_FOUND_IN_SYSTEM);
			return Collections.EMPTY_LIST;
		}

		return users.stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		User user = findUserByIdOrThrow(id);
		return toResponseDTO(user);
	}

	@Override
	public UserResponseDTO getUserByName(String name) {
		User user = userRepository.findByFullName(name)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_NAME));
		return toResponseDTO(user);
	}

	@Override
	public UserResponseDTO deleteUser(Long id) {
		User user = findUserByIdOrThrow(id);
		userRepository.deleteById(id);
		return toResponseDTO(user);
	}

	@Override
	public UserResponseDTO saveUser(SignupRequestDTO request) throws GenericStoreException {
		validateNewUser(request);

		User user = buildUserFromRequest(request);
		user.setRoles(resolveRoles(request.getRoles()));

		userRepository.save(user);
		return toResponseDTO(user);
	}

	@Override
	public UserResponseDTO updateUser(SignupRequestDTO request) {
		User existingUser = userRepository.findByUsername(request.getUsername()).orElse(new User());

		existingUser.setFullName(request.getFullName());
		existingUser.setEmail(request.getEmail());
		existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
		existingUser.setRoles(resolveRoles(request.getRoles()));

		userRepository.save(existingUser);
		return toResponseDTO(existingUser);
	}

	private void validateNewUser(SignupRequestDTO request) throws GenericStoreException {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new GenericStoreException(USERNAME_TAKEN);
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new GenericStoreException(EMAIL_IN_USE);
		}
	}

	private Set<Role> resolveRoles(Set<String> strRoles) {
		Set<Role> roles = new HashSet<>();

		if (strRoles == null || strRoles.isEmpty()) {
			roles.add(getRoleOrThrow(ERole.ROLE_USER));
		} else {
			for (String role : strRoles) {
				ERole roleEnum = mapStringToRole(role);
				roles.add(getRoleOrThrow(roleEnum));
			}
		}
		return roles;
	}

	private ERole mapStringToRole(String role) {
		return switch (role.toLowerCase()) {
			case "admin" -> ERole.ROLE_ADMIN;
			case "mod" -> ERole.ROLE_MODERATOR;
			default -> ERole.ROLE_USER;
		};
	}

	private Role getRoleOrThrow(ERole roleName) {
		return roleRepository.findByName(roleName)
				.orElseThrow(() -> new NotFoundException(String.format(ROLE_NOT_FOUND, roleName)));
	}

	private User findUserByIdOrThrow(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ID));
	}

	private User buildUserFromRequest(SignupRequestDTO request) {
		return User.builder()
				.idNumber(request.getIdNumber())
				.fullName(request.getFullName())
				.username(request.getUsername())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.build();
	}

	private UserResponseDTO toResponseDTO(User user) {
		return MapperUtil.mapToDTO(user, UserResponseDTO.class);
	}
}
