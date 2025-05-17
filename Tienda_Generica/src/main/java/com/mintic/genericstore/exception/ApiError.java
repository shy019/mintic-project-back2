package com.mintic.genericstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

	private final int statusCode;  
	private final String errorType;  
	private final String errorMessage;  

}
