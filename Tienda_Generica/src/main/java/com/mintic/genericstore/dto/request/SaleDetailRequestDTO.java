package com.mintic.genericstore.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
public class SaleDetailRequestDTO {

	@Id
	@NotNull(message = "Sale detail code must be provided")
	private long saleDetailId;

	@NotNull(message = "Product quantity must be provided")
	@Positive(message = "Product quantity must be greater than zero")
	private long productQuantity;

	@Valid
	@NotNull(message = "Product must be provided")
	private ProductRequestDTO product;

	@Valid
	@NotNull(message = "Sale must be provided")
	private SaleRequestDTO sale;

	@NotNull(message = "Total value must be provided")
	@Positive(message = "Total value must be greater than zero")
	private double totalValue;

	@NotNull(message = "Sale value must be provided")
	@Positive(message = "Sale value must be greater than zero")
	private double saleValue;

	@NotBlank(message = "Currency type is required")
	@Pattern(regexp = "USD|COP", message = "Currency must be either 'USD' or 'COP'")
	private String concurrencyType;

	@Valid
	@NotNull(message = "Branch must be provided")
	private BranchRequestDTO branch;
}
