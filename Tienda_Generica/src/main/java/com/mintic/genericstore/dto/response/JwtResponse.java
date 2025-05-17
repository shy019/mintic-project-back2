package com.mintic.genericstore.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtResponse {

	private String token;
	private final String type = "Bearer";
	private Long id;
	private String username;
	private String name;
	private String email;
	private List<String> roles;
}
