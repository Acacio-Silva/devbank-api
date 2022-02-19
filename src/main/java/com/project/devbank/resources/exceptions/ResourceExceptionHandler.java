package com.project.devbank.resources.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.devbank.service.exceptions.DataIntegrityViolation;
import com.project.devbank.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e){
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	} 
	
	@ExceptionHandler(DataIntegrityViolation.class)
	public ResponseEntity<StandardError> DataIntegrityViolation(DataIntegrityViolation e){
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> constraintViolation(MethodArgumentNotValidException e){
		ValidationError error = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos!");
        for(FieldError x : e.getBindingResult().getFieldErrors()) {
        	error.addErrors(x.getField(), x.getDefaultMessage());
        }
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
