package com.mintic.genericstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

	private Long productId;  
	private String productName;  
	private SupplierResponseDTO supplier;  
	private Double purchasePrice;  
	private Double purchaseTax;  
	private Double salePrice;  

}
