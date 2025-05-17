package com.mintic.genericstore.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ProductRequestDTO {

	@Id
	private Long productId;

	@NotBlank(message = "Product name must be provided")
	@Size(max = 50, message = "Product name should not exceed 50 characters")
	private String productName;

	@Valid
	private SupplierRequestDTO supplier;

	@NotNull(message = "Product purchase price must be provided")
	private Double purchasePrice;

	@NotNull(message = "Purchase Tax must be provided")
	private Double purchaseTax;

	@NotNull(message = "Product sale price must be provided")
	private Double salePrice;
}
