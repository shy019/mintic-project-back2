package com.mintic.tiendagenerica.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Client {
	
	@Id
	private Long cedulaCliente;

	private String nombreCliente;

	private String direccionCliente;

	private String telefonoCliente;

	private String emailCliente;

	public Long getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(Long cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}	
	
	public Client(Long cedulaCliente, @NotBlank @Size(max = 30) String nombreCliente,
			@NotBlank @Size(max = 60) String direccionCliente, @NotBlank @Size(max = 30) String telefonoCliente,
			@NotBlank @Size(max = 60) String emailCliente) {
		super();
		this.cedulaCliente = cedulaCliente;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.telefonoCliente = telefonoCliente;
		this.emailCliente = emailCliente;
	}

	public Client() {
		super();
	}

}
