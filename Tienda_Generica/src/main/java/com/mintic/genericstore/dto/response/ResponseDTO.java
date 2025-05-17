package com.mintic.genericstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {

	private int quantity;  
	private String message;  
	private List<ProductResponseDTO> savedProducts;  
	private List<ProductResponseDTO> notSavedProducts;  

}
