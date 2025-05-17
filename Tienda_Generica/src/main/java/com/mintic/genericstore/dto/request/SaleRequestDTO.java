package com.mintic.genericstore.dto.request;

import com.mintic.genericstore.model.Client;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {

	@Id
	@NotNull(message = "Sale code is required")
	@Min(value = 1, message = "Invalid sale code")
	@Max(value = 9999999999L, message = "Invalid sale code")
	private Long saleId;

	@Valid
	@NotNull(message = "Client ID is required")
	private Client client;

	@NotNull(message = "User ID is required")
	private Long userId;

	@NotNull(message = "Sale value is required")
	@Min(value = 0, message = "Sale value cannot be negative")
	private Double saleValue;

	@NotNull(message = "TAX value is required")
	@Min(value = 0, message = "TAX value cannot be negative")
	private Double saleTax;

	@NotNull(message = "Total sale value is required")
	@Min(value = 0, message = "Total sale value cannot be negative")
	private Double totalSale;
}
