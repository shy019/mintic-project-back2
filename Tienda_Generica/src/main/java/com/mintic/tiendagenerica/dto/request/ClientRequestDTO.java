package com.mintic.tiendagenerica.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Validated
public class ClientRequestDTO {
	
	@Min(value = 1, message = "Cédula no válida")
	@Max(value = 999999999, message = "Cédula no válida")
	@Id
	private Long cedulaCliente;
	
	@NotBlank(message = "Debe digitar el nombre del cliente")
	@Size(max = 30)
	private String nombreCliente;
	
	@NotBlank(message = "Debe digitar la dirección de residencia del cliente")
	@Size(max = 60)
	private String direccionCliente;
	
	@NotBlank(message = "Debe digitar el número de teléfono del cliente")
	@Size(max = 30)
	private String telefonoCliente;
	
	@NotBlank(message = "Debe digitar el email del cliente")
	@Size(max = 60)
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
	
	public ClientRequestDTO(Long cedulaCliente, @NotBlank @Size(max = 30) String nombreCliente,
			@NotBlank @Size(max = 60) String direccionCliente, @NotBlank @Size(max = 30) String telefonoCliente,
			@NotBlank @Size(max = 60) String emailCliente) {
		super();
		this.cedulaCliente = cedulaCliente;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.telefonoCliente = telefonoCliente;
		this.emailCliente = emailCliente;
	}
	
	public ClientRequestDTO() {
		super();
	}

}
