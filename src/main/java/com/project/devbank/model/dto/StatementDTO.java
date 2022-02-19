package com.project.devbank.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.devbank.model.enums.OperationType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StatementDTO {

	private Integer id;
	private BigDecimal valor;
	private LocalDateTime moment;
	private OperationType operationType;
	
	
}
