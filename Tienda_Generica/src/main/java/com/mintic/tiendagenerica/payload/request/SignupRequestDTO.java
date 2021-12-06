package com.mintic.tiendagenerica.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequestDTO {
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> role;

	@NotBlank
	@Size(max = 150)
	private String name;

	private Long cedula;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SignupRequestDTO(@NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, Set<String> role, @NotBlank @Size(max = 150) String name,
			Long cedula, @NotBlank @Size(min = 6, max = 40) String password) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.name = name;
		this.cedula = cedula;
		this.password = password;
	}

	public SignupRequestDTO() {
		super();
	}

	@Override
	public String toString() {
		return "SignupRequestDTO [username=" + username + ", email=" + email + ", role=" + role + ", name=" + name
				+ ", cedula=" + cedula + ", password=" + password + "]";
	}

}
