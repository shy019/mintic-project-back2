package com.mintic.genericstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

	@Id
	private Long productId;

	private String productName;

	@DBRef
	private Supplier supplier;

	private Double purchasePrice;

	private Double purchaseTax;

	private Double salePrice;

}
