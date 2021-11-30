package com.mintic.tiendagenerica.dto.response;

import java.util.Set;

public class UserResponseDTO {

	private Long cedula;
	private String name;
	private String email;
	private String username;
	private Set<String> roles;

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

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

	public UserResponseDTO(Long cedula, String name, String email, String username) {
		super();
		this.cedula = cedula;
		this.name = name;
		this.email = email;
		this.username = username;
	}

	public UserResponseDTO() {
		super();
	}

	@Override
	public String toString() {
		return "UserResponseDTO [cedula=" + cedula + ", name=" + name + ", email=" + email + ", username=" + username
				+ "]";
	}

}
