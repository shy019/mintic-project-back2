package com.mintic.genericstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDTO {

	private Long saleId;
	private ClientResponseDTO client;
	private UserResponseDTO user;
	private Double saleValue;  
	private Double saleTax;  
	private Double totalSale;  

}
