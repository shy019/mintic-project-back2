package com.mintic.genericstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequestDTO {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
