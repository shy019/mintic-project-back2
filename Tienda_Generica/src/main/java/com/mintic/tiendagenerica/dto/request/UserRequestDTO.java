package com.mintic.tiendagenerica.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class UserRequestDTO {

	@Id
	@NotNull(message = "El número de cédula es requerido")
	@Min(value = 1, message = "Cédula no válida")
	@Max(value = 9999999999L, message = "Cédula no válida")
	private Long cedula;

	@NotBlank(message = "El nombre de usuario es requerido")	
	@Size(max = 150)
	private String name;

	@NotBlank(message = "El correo electrónico del usuario es requerido")
	@Email(regexp = "[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", message = "El email digitado no tiene el formato esperado Ej.ejemplo@gmail.com")
	@Size(max = 50)
	private String email;

	@NotBlank(message = "Debe digitar un nombre de usuario")
	@Size(max = 20, message = "El usuario no puede tener más de 20 caracteres")
	private String username;

	@NotBlank(message = "La contraseña es requerida")
	@Size(max = 120)
	private String password;

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRequestDTO(@NotNull Long cedula, @NotBlank @Size(max = 150) String name,
			@NotBlank @Size(max = 50) String email, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 120) String password) {
		super();
		this.cedula = cedula;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public UserRequestDTO() {
		super();
	}

	@Override
	public String toString() {
		return "UserRequestDTO [cedula=" + cedula + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + "]";
	}

}
