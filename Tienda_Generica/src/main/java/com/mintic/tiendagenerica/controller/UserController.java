package com.mintic.tiendagenerica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.tiendagenerica.dto.response.UserResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.payload.request.SignupRequestDTO;
import com.mintic.tiendagenerica.service.IUserService;

@RestController
@RequestMapping("/api/tiendagenerica/user")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class UserController {

	@Qualifier("UserService")
	IUserService iUserService;

	public UserController(IUserService iUserService) {
		this.iUserService = iUserService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<UserResponseDTO>> getUsers() throws TiendaGenericaException {
		return new ResponseEntity<List<UserResponseDTO>>(iUserService.getUsers(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{cedula}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<UserResponseDTO> getUser(@PathVariable("cedula") Long cedula) throws TiendaGenericaException {
		return new ResponseEntity<UserResponseDTO>(iUserService.getUserByCedula(cedula), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/JSON" }, produces = {
			"application/JSON" })
	public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody SignupRequestDTO user)
			throws TiendaGenericaException {

		return new ResponseEntity<UserResponseDTO>(iUserService.saveUser(user), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/JSON" }, consumes = {
			"application/JSON" })
	public ResponseEntity<UserResponseDTO> editar(@RequestBody SignupRequestDTO user) throws TiendaGenericaException {
		return new ResponseEntity<UserResponseDTO>(iUserService.updateUser(user), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{cedula}", method = RequestMethod.DELETE, produces = { "application/JSON" })
	public ResponseEntity<UserResponseDTO> delete(@PathVariable("cedula") long cedula) throws TiendaGenericaException {
		return new ResponseEntity<UserResponseDTO>(iUserService.deleteUser(cedula), HttpStatus.OK);
	}
}