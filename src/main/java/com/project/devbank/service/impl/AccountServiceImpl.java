package com.project.devbank.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.devbank.model.Account;
import com.project.devbank.model.Client;
import com.project.devbank.model.Statement;
import com.project.devbank.model.dto.AccountById;
import com.project.devbank.model.dto.AccountDTO;
import com.project.devbank.model.dto.request.AccountRequest;
import com.project.devbank.model.dto.request.ClientRequest;
import com.project.devbank.model.enums.OperationType;
import com.project.devbank.repositories.AccountRepository;
import com.project.devbank.repositories.ClientRepository;
import com.project.devbank.repositories.StatementRepository;
import com.project.devbank.service.AccountService;
import com.project.devbank.service.exceptions.DataIntegrityViolation;
import com.project.devbank.service.exceptions.ObjectNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private StatementRepository statementRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public List<AccountDTO> findAll() {	
		List<Account> accounts = accountRepository.findAll();
		List<AccountDTO> list = accounts.stream().map(x -> mapper.map(x, AccountDTO.class)).collect(Collectors.toList());
		return list;
	}
	
	
	@Override
	public AccountById findById(Integer id) {
		Optional<Account> account = accountRepository.findById(id);
		if(account.isPresent()) {
			return mapper.map(account.get(), AccountById.class);
		}
		throw new ObjectNotFoundException("Conta com ID: "+ id + " Não encontrada!");
	}

	@Override
	public AccountRequest openAccount(ClientRequest clientRequest) {
		Optional<Client> clientOp = clientRepository.findByCpf(clientRequest.getCpf());
		if(clientOp.isPresent()) {
			throw new DataIntegrityViolation("Usuario já cadastrado no cpf: " + clientRequest.getCpf());
		}
		clientRequest.setPassword(encoder.encode(clientRequest.getPassword()));
		clientRequest.getRoles().add("CLIENT");
		Account account = new Account(new BigDecimal("0"), LocalDateTime.now(), mapper.map(clientRequest, Client.class));
		accountRepository.save(account);
		return mapper.map(account, AccountRequest.class);
	}

	
	@org.springframework.transaction.annotation.Transactional
	@Override
	public AccountRequest transfer(Integer id, Integer number, BigDecimal value) {
		
		Optional<Account> account = accountRepository.findById(id);
		
		if(account.get().getSaldo().doubleValue()>=value.doubleValue()) {
		
			account.get().setSaldo(account.get().getSaldo().subtract(value));
			Statement statement = new Statement(value, LocalDateTime.now(), OperationType.TRANSFER_DEBIT);
			account.get().getStatement().add(statement);
			
			Optional<Account> account2 = accountRepository.findById(number);
			account2.get().setSaldo(account2.get().getSaldo().add(value));
			Statement statement2 = new Statement(value, LocalDateTime.now(), OperationType.TRANSFER_CREDIT);
			account2.get().getStatement().add(statement2);
			
			statementRepository.saveAll(Arrays.asList(statement, statement2));
			accountRepository.save(mapper.map(account.get(), Account.class));
			accountRepository.save(mapper.map(account2.get(), Account.class));
			
			return mapper.map(account.get(), AccountRequest.class);
			
		}
		
		throw new DataIntegrityViolation("Saldo Insuficiente para a transferencia! Seu saldo é: "+ account.get().getSaldo() );
	}

	@Override
	public AccountRequest debit(Integer id, BigDecimal value) {
		
		Optional<Account> account = accountRepository.findById(id);		
		
		if(account.get().getSaldo().doubleValue()>=value.doubleValue()) {
		
			account.get().setSaldo(account.get().getSaldo().subtract(value));
			Statement statement = new Statement(value, LocalDateTime.now(), OperationType.DEBIT);
			account.get().getStatement().add(statement);
			
			statementRepository.save(statement);
			accountRepository.save(account.get());
			
			return mapper.map(account.get(), AccountRequest.class);
		}
		throw new DataIntegrityViolation("Saldo Insuficiente!");
	}

	@Override
	public AccountRequest credit(Integer id, BigDecimal value) {
		Optional<Account> account = accountRepository.findById(id);
		
		account.get().setSaldo(account.get().getSaldo().add(value));
		Statement statement = new Statement(value, LocalDateTime.now(), OperationType.CREDIT);
		account.get().getStatement().add(statement);
		
		statementRepository.save(statement);
		accountRepository.save(account.get());
		
		return mapper.map(account.get(), AccountRequest.class);
	}


	@Override
	public AccountRequest delete(Integer id) {
		Optional<Account> account = accountRepository.findById(id);
		if(account.isPresent()) {
			if(account.get().getSaldo().doubleValue() == 0 ) {
				accountRepository.delete(account.get());
				return mapper.map(account.get(), AccountRequest.class);
			}
			throw new DataIntegrityViolation("Sua conta ainda possui saldo de: "+ account.get().getSaldo());
		}
		throw new DataIntegrityViolation("Conta invalida no id: " + account.get().getId());
	}

}
