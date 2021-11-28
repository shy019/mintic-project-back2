package com.mintic.tiendagenerica.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.response.UserResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.jwt.ERole;
import com.mintic.tiendagenerica.model.jwt.Role;
import com.mintic.tiendagenerica.model.jwt.User;
import com.mintic.tiendagenerica.payload.request.SignupRequestDTO;
import com.mintic.tiendagenerica.repository.IRoleRepository;
import com.mintic.tiendagenerica.repository.IUserRepository;
import com.mintic.tiendagenerica.service.IUserService;

@Service
public class UserService implements IUserService {

	@Qualifier("PasswordEncoder")
	private PasswordEncoder passwordEncoder;
	private IUserRepository iUserRepository;
	private IRoleRepository iRoleRepository;
	private ModelMapper modelMapper;

	public UserService(PasswordEncoder passwordEncoder, IUserRepository iUserRepository,
			IRoleRepository iRoleRepository, ModelMapper modelMapper) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.iUserRepository = iUserRepository;
		this.iRoleRepository = iRoleRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<UserResponseDTO> getUsers() throws TiendaGenericaException {

		List<UserResponseDTO> usuarios = iUserRepository.findAll().stream()
				.map(user -> this.modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());

		if (usuarios.isEmpty())
			throw new TiendaGenericaException("No hay usuarios en el sistema");
		else
			return usuarios;

	}

	@Override
	public UserResponseDTO getUserByCedula(Long cedula) throws TiendaGenericaException {

		return this.modelMapper.map(iUserRepository.findUserByCedula(cedula).orElseThrow(
				() -> new TiendaGenericaException("No hay un usuario con esa cedula")), UserResponseDTO.class);

	}

	@Override
	public UserResponseDTO deleteUser(Long cedula) throws TiendaGenericaException {

		User user = iUserRepository.findUserByCedula(cedula)
				.orElseThrow(() -> new TiendaGenericaException("No hay un usuario con esa cedula"));

		iUserRepository.deleteByCedula(cedula);
		return this.modelMapper.map(user, UserResponseDTO.class);

	}

	@Override
	public UserResponseDTO getUserByName(String nombre) throws TiendaGenericaException {

		return this.modelMapper.map(iUserRepository.findUserByName(nombre).orElseThrow(
				() -> new TiendaGenericaException("No hay un usuario con ese nombre")), UserResponseDTO.class);

	}

	@Override
	public UserResponseDTO updateUser(SignupRequestDTO user) throws TiendaGenericaException {

		if (!iUserRepository.existsByUsername(user.getUsername()) || !iUserRepository.existsByEmail(user.getEmail())) {
			throw new TiendaGenericaException("El usuario no existe");
		}

		// Create new user's account
		User newUser = new User(user.getCedula(), user.getName(), user.getEmail(), user.getUsername(),
				passwordEncoder.encode(user.getPassword()));

		Set<String> strRoles = user.getRole();
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

		newUser.setRoles(roles);
		iUserRepository.save(newUser);

		return this.modelMapper.map(newUser, UserResponseDTO.class);

	}

	@Override
	public UserResponseDTO saveUser(SignupRequestDTO user) throws TiendaGenericaException {

		if (iUserRepository.existsByUsername(user.getUsername())) {
			throw new TiendaGenericaException("El usuario ya esta tomado");
		}

		if (iUserRepository.existsByEmail(user.getEmail())) {
			throw new TiendaGenericaException("El correo ya esta en uso");
		}

		// Create new user's account
		User newUser = new User(user.getCedula(), user.getName(), user.getEmail(), user.getUsername(),
				passwordEncoder.encode(user.getPassword()));

		Set<String> strRoles = user.getRole();
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

		newUser.setRoles(roles);
		iUserRepository.save(newUser);

		return this.modelMapper.map(newUser, UserResponseDTO.class);

	}

}
