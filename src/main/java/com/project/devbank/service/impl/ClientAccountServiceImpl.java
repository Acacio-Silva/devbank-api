package com.project.devbank.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.devbank.model.Account;
import com.project.devbank.model.Client;
import com.project.devbank.model.dto.request.AccountRequest;
import com.project.devbank.model.dto.request.ClientRequest;
import com.project.devbank.model.dto.request.UserLogin;
import com.project.devbank.repositories.AccountRepository;
import com.project.devbank.repositories.ClientRepository;
import com.project.devbank.service.ClientAccountService;
import com.project.devbank.service.exceptions.DataIntegrityViolation;

@Service
public class ClientAccountServiceImpl implements ClientAccountService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public AccountRequest openAccount(ClientRequest clientRequest) {
		Optional<Client> client = clientRepository.findByCpf(clientRequest.getCpf());
		if(!client.isPresent()) {
			clientRequest.setPassword(encoder.encode(clientRequest.getPassword()));
			clientRequest.getRoles().add("CLIENT");
			Account account = new Account(new BigDecimal("0"), LocalDateTime.now(), mapper.map(clientRequest, Client.class));
			accountRepository.save(account);
			return mapper.map(account, AccountRequest.class);
		}
		throw new DataIntegrityViolation("Usuario já cadastrado no CPF: "+ clientRequest.getCpf());
	}

	@Override
	public AccountRequest transfer(Integer id, BigDecimal value, Authentication authentication) {
		
		Optional<Client> client = clientRepository.findByCpf(authentication.getName());
		Optional<Account> acc = accountRepository.findByClient(client.get());
		if(acc.isPresent()) {
			accountServiceImpl.transfer(client.get().getId(), id, value);	
		}		
		return null;
	}

	@Override
	public UserLogin login(UserLogin userLogin) {
		Optional<Client> client = clientRepository.findByCpf(userLogin.getCpf());
		if(client.isPresent()) {
			if((client.get().getCpf().equals(userLogin.getCpf())) && (encoder.matches(userLogin.getPassword(), client.get().getPassword()))) {
				return mapper.map(client.get(), UserLogin.class);
			}
			throw new DataIntegrityViolation("Cpf ou senha invalida ");
		}
		throw new DataIntegrityViolation("Cliente não encotrado no cpf! " + userLogin.getCpf());
	}
	
	
}
