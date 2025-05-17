package com.mintic.genericstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ClientRequestDTO {

	@NotNull(message = "The identification number is required")
	@Min(value = 1, message = "Invalid identification number")
	@Max(value = 9999999999L, message = "Invalid identification number")
	@Id
	private Long clientId;

	@NotBlank(message = "Client name is required")
	@Size(max = 30, message = "Client name should not be more than 30 characters")
	private String clientName;

	@NotBlank(message = "Client's residential address is required")
	@Size(max = 60, message = "Address should not be more than 60 characters")
	private String clientAddress;

	@NotBlank(message = "Client's phone number is required")
	@Pattern(regexp = "[300-399]{3}-[0-9]{7}", message = "Invalid phone number. Example: 312-5483322")
	@Size(max = 11, message = "Phone number should not be more than 11 characters")
	private String clientPhoneNumber;

	@NotBlank(message = "Client's email is required")
	@Email(regexp = "[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", message = "Invalid email format. Example: example@gmail.com")
	@Size(min = 3, message = "Email should have at least 3 characters")
	@Size(max = 60, message = "Email should not be more than 60 characters")
	private String clientEmail;
}
