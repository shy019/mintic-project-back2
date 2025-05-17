package com.mintic.genericstore.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationError {
	private final String field;
	private final String message;
}
