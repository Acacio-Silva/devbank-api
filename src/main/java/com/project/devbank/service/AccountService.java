package com.project.devbank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.devbank.model.dto.AccountById;
import com.project.devbank.model.dto.AccountDTO;
import com.project.devbank.model.dto.request.AccountRequest;
import com.project.devbank.model.dto.request.ClientRequest;

@Service
public interface AccountService {

	List<AccountDTO> findAll();
	AccountById findById(Integer id);
	AccountRequest openAccount(ClientRequest clientRequest);
	AccountRequest transfer(Integer id, Integer AccountNumber, BigDecimal value);
	AccountRequest credit(Integer id, BigDecimal value);
	AccountRequest debit(Integer id, BigDecimal value);
	AccountRequest delete(Integer id);
}
