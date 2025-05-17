package com.mintic.genericstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

	@Id
	private Long saleId;

	@DBRef
	private Client client;

	@DBRef
	private User user;

	private Double saleValue;

	private Double saleTax;

	private Double totalSale;

}
