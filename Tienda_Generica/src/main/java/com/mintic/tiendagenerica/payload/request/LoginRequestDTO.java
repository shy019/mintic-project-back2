package com.mintic.tiendagenerica.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequestDTO {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

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

	public LoginRequestDTO(@NotBlank String username, @NotBlank String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginRequestDTO() {
		super();
	}

	@Override
	public String toString() {
		return "LoginRequestDTO [username=" + username + ", password=" + password + "]";
	}

}
