package com.mintic.genericstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequestDTO {

	@Min(value = 1, message = "Invalid NIT")
	@Max(value = 999999999, message = "Invalid NIT")
	@Id
	private Long SupplierId;

	@NotBlank(message = "Please enter the city where the supplier is located")
	@Size(max = 150, message = "City name must be at most 150 characters")
	private String supplierCity;

	@NotBlank(message = "Please enter the supplier's address")
	@Size(max = 60, message = "Address must be at most 60 characters")
	private String supplierAddress;

	@NotBlank(message = "Please enter the supplier's name")
	@Size(max = 30, message = "Supplier's name must be at most 30 characters")
	private String supplierName;

	@NotBlank(message = "Please enter the supplier's contact phone number - Example: 300-1234567")
	@Pattern(regexp = "[300-399]{3}-[0-9]{7}", message = "The entered number is incorrect. Example: 300-1234567")
	@Size(max = 120, message = "Phone number must be at most 120 characters")
	private String supplierPhone;
}
