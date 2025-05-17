package com.mintic.genericstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {

	private Long clientId;       
	private String clientName;   
	private String clientAddress;  
	private String clientPhoneNumber;
	private String clientEmail;  

}
