package com.mintic.genericstore.service;

import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.UserResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;

import java.util.List;

public interface UserService {
	List<UserResponseDTO> getUsers() throws GenericStoreException;

	UserResponseDTO getUserById(Long id) throws GenericStoreException;

	UserResponseDTO saveUser(SignupRequestDTO user) throws GenericStoreException;

	UserResponseDTO deleteUser(Long id) throws GenericStoreException;

	UserResponseDTO updateUser(SignupRequestDTO user) throws GenericStoreException;

	UserResponseDTO getUserByName(String name) throws GenericStoreException;
}
