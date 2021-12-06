package com.mintic.tiendagenerica.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Validated
public class ClientRequestDTO {
	
	@NotNull(message = "El número de cédula es requerido")
	@Min(value = 1, message = "Cédula no válida")
	@Max(value = 9999999999L, message = "Cédula no válida")
	@Id
	private Long cedulaCliente;
	
	@NotBlank(message = "Debe digitar el nombre del cliente")
	@Size(max = 30, message = "El nombre no debe tener más de 30 caracteres")
	private String nombreCliente;
	
	@NotBlank(message = "Debe digitar la dirección de residencia del cliente")
	@Size(max = 60, message = "La dirección no debe tener más de 60 caracteres")
	private String direccionCliente;
	
	@NotBlank(message = "Debe digitar el número de teléfono del cliente")
	@Pattern(regexp = "[300-399]{3}-[0-9]{7}", message = "El teléfono digitado no es correcto Ej.312-5483322")
	@Size(max = 11, message = "El número de teléfono no debe tener más de 11 caracteres")
	private String telefonoCliente;
	
	@NotBlank(message = "Debe digitar el email del cliente")
	@Email(regexp = "[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", message = "El email digitado no tiene el formato esperado Ej.ejemplo@gmail.com")
	@Size(min = 3, message = "El email debe tener más de 3 caracteres")
	@Size(max = 60, message = "El email no debe tener más de 60 caracteres")
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
