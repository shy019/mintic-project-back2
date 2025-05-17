package com.mintic.genericstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

	@NotNull(message = "ID number is required")
	@Min(value = 1, message = "Invalid ID number")
	@Max(value = 9999999999L, message = "Invalid ID number")
	private Long idNumber;

	@NotBlank(message = "Username is required")
	@Size(max = 150, message = "Username cannot exceed 150 characters")
	private String name;

	@NotBlank(message = "User email is required")
	@Email(regexp = "[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", message = "Invalid email format. Example: example@gmail.com")
	@Size(max = 50, message = "Email cannot exceed 50 characters")
	private String email;

	@NotBlank(message = "Username is required")
	@Size(max = 20, message = "Username cannot exceed 20 characters")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(max = 120, message = "Password cannot exceed 120 characters")
	private String password;

	@Override
	public String toString() {
		return "UserRequestDTO [idNumber=" + idNumber + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + "]";
	}
}
