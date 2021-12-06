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

import com.mintic.tiendagenerica.dto.request.ClientRequestDTO;
import com.mintic.tiendagenerica.dto.response.ClientResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.service.IClientService;

@RestController
@RequestMapping("/api/tiendagenerica/client")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class ClientController {
	
	@Qualifier("ClientService")
	IClientService iClientService;
	
	public ClientController(IClientService iClientService) {
		this.iClientService = iClientService;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<List<ClientResponseDTO>> getClienst() throws TiendaGenericaException {
		return new ResponseEntity<List<ClientResponseDTO>>(iClientService.getClients(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{cedulaCliente}", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<ClientResponseDTO> getClient(@PathVariable("cedulaCliente") Long cedulaCliente)
			throws TiendaGenericaException {
		return new ResponseEntity<ClientResponseDTO>(iClientService.getClientByCedula(cedulaCliente), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/JSON" }, produces = {
			"application/JSON" })
	public ResponseEntity<ClientResponseDTO> save(@Valid @RequestBody ClientRequestDTO client)
			throws TiendaGenericaException {

		return new ResponseEntity<ClientResponseDTO>(iClientService.saveClient(client), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/JSON" }, consumes = {
			"application/JSON" })
	public ResponseEntity<ClientResponseDTO> editar(@RequestBody ClientRequestDTO client)
			throws TiendaGenericaException {
		return new ResponseEntity<ClientResponseDTO>(iClientService.updateClient(client), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(value = "/{cedulaCliente}", method = RequestMethod.DELETE, produces = { "application/JSON" })
	public ResponseEntity<ClientResponseDTO> delete(@PathVariable("cedulaCliente") Long cedulacliente)
			throws TiendaGenericaException {
		return new ResponseEntity<ClientResponseDTO>(iClientService.deleteClient(cedulacliente), HttpStatus.OK);
	}

}
