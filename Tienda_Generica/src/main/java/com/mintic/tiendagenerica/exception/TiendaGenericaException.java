package com.mintic.tiendagenerica.exception;

public class TiendaGenericaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public TiendaGenericaException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
