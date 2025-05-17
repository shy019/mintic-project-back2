package com.mintic.genericstore.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

	@Id
	private Long clientId;

	@NotBlank
	@Size(max = 30)
	private String clientName;

	@NotBlank
	@Size(max = 60)
	private String clientAddress;

	@NotBlank
	@Size(max = 30)
	private String clientPhoneNumber;

	@NotBlank
	@Size(max = 60)
	private String clientEmail;
}
