package com.mintic.genericstore.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExchangeRateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExchangeRateException(String message) {
		super(message);
	}

	public ExchangeRateException(String message, Exception ex) {
		super(message);
	}
}
