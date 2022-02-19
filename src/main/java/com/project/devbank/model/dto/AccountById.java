package com.project.devbank.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountById {

	private Integer id;
	private BigDecimal saldo;
	private ClientAllDTO client;
	private LocalDateTime moment;
	private List<StatementDTO> statement;
	
}
