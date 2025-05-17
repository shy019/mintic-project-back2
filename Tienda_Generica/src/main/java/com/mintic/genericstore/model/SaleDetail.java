package com.mintic.genericstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sale_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetail {

	@Id
	private long saleDetailId;

	private long productQuantity;

	@DBRef
	private Product product;

	@DBRef
	private Sale sale;

	private double totalValue;

	private double saleValue;

	private String concurrencyType;

	@DBRef
	private Branch branch;

}
