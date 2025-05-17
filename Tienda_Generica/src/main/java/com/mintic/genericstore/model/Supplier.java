package com.mintic.genericstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

	@Id
	private Long supplierId;

	private String supplierCity;

	private String supplierAddress;

	private String supplierName;

	private String supplierPhone;

}
