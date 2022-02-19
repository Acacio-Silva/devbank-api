package com.project.devbank.service.exceptions;

public class DataIntegrityViolation extends RuntimeException {

	private static final long serialVersionUID = -3799373243142835783L;

	public DataIntegrityViolation(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataIntegrityViolation(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
