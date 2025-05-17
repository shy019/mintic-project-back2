package com.mintic.genericstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

	@Id
	private Long saleDetailCode;

	private String branchName;

	private String city;
}
