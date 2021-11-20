package com.mintic.tiendagenerica.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class UserRequestDTO {

	@Id
	@NotNull
	private Long cedula;

	@NotBlank
	@Size(max = 150)
	private String name;

	@NotBlank
	@Size(max = 50)
	private String email;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
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
