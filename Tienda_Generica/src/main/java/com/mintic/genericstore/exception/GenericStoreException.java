package com.mintic.genericstore.exception;

public class GenericStoreException extends Exception {

	private static final long serialVersionUID = 1L;

	public GenericStoreException(String message) {
		super(message);
	}

	public GenericStoreException(String message, Exception ex) {
		super(message);
	}
}
