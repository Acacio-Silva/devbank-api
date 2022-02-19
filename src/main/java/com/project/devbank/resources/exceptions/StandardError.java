package com.project.devbank.resources.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class StandardError implements Serializable {

	private static final long serialVersionUID = 8678634021688283262L;
	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	
}
