package com.mintic.genericstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailResponseDTO {

	@Id
	private long saleDetailCode;  

	private long productQuantity;  

	private ProductResponseDTO productCode;  

	private SaleResponseDTO saleCode;  

	private double totalValue;  

	private double saleValue;  
	private BranchResponseDTO branch;  

}
