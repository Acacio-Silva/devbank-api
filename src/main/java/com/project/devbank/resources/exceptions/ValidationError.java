package com.project.devbank.resources.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandardError  {

	private static final long serialVersionUID = -6281949480545322714L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(LocalDateTime timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
	
	
}
