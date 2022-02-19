package com.project.devbank.service;

import java.math.BigDecimal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.project.devbank.model.dto.request.AccountRequest;
import com.project.devbank.model.dto.request.ClientRequest;
import com.project.devbank.model.dto.request.UserLogin;

@Service
public interface ClientAccountService {

	AccountRequest openAccount(ClientRequest clientRequest);
	UserLogin login(UserLogin userLogin);
	AccountRequest transfer(Integer id, BigDecimal value, Authentication authentication);
	
}
