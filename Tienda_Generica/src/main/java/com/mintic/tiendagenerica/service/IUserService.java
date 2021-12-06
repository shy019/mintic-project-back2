package com.mintic.tiendagenerica.service;

import java.util.List;

import com.mintic.tiendagenerica.dto.response.UserResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.payload.request.SignupRequestDTO;

public interface IUserService {
	public List<UserResponseDTO> getUsers() throws TiendaGenericaException;

	public UserResponseDTO getUserByCedula(Long cedula) throws TiendaGenericaException;

	public UserResponseDTO saveUser(SignupRequestDTO user) throws TiendaGenericaException;

	public UserResponseDTO deleteUser(Long cedula) throws TiendaGenericaException;

	public UserResponseDTO updateUser(SignupRequestDTO user) throws TiendaGenericaException;

	public UserResponseDTO getUserByName(String nombre) throws TiendaGenericaException;
}
