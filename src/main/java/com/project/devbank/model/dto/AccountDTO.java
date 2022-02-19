package com.project.devbank.model.dto;

import java.math.BigDecimal;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountDTO {

	private Integer id;
	private BigDecimal saldo;
	private ClientAllDTO client;
	
}
