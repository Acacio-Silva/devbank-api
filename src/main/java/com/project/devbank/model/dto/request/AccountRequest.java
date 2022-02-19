package com.project.devbank.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.devbank.model.Statement;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AccountRequest {

	private Integer id;
	private BigDecimal saldo;
	private List<Statement> statement = new ArrayList<>();
	private LocalDateTime moment;
	private ClientRequest client;
	
}
