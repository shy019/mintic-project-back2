package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.UserResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class UserControllerImpl implements com.mintic.genericstore.controller.UserController {

	@Qualifier("UserServiceImpl")
	UserService userService;

	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	public ResponseEntity<List<UserResponseDTO>> getUsers() throws GenericStoreException {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id) throws GenericStoreException {
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}

	public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody SignupRequestDTO user) throws GenericStoreException {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	public ResponseEntity<UserResponseDTO> update(@RequestBody SignupRequestDTO user) throws GenericStoreException {
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
	}

	public ResponseEntity<UserResponseDTO> delete(@PathVariable("id") long id) throws GenericStoreException {
		return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
	}
}
