package com.mintic.genericstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequestDTO {

	@Id
	private Long saleDetailCode;

	private String branchName;

	private String city;
}
