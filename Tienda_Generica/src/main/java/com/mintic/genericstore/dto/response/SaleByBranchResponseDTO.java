package com.mintic.genericstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleByBranchResponseDTO {

	private String branch;  
	private Long soldProductsQuantity;  
	private Double totalSaleValue;  
	private Double totalSaleTax;  
	private Double totalSaleWithTax;  

}
