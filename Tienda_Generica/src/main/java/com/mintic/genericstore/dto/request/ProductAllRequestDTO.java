package com.mintic.genericstore.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAllRequestDTO {

	@Id
	private Long productCode;

	@NotNull(message = "Product name is required")
	@Size(max = 50, message = "Product name cannot exceed 50 characters")
	private String productName;

	@NotNull(message = "Supplier NIT is required")
	private Long SupplierId;

	@NotNull(message = "Purchase price is required")
	private Double purchasePrice;

	@NotNull(message = "Purchase VAT is required")
	private Double purchaseTAX;

	@NotNull(message = "Sale price is required")
	private Double salePrice;
}
