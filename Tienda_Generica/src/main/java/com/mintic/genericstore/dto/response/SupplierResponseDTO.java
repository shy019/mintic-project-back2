package com.mintic.genericstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponseDTO {

	private Long SupplierId;  
	private String supplierCity;  
	private String supplierAddress;  
	private String supplierName;  
	private String supplierPhone;  

}
