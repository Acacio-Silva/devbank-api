package com.project.devbank.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.devbank.model.Account;
import com.project.devbank.model.Client;
import com.project.devbank.model.Statement;
import com.project.devbank.model.enums.OperationType;
import com.project.devbank.repositories.AccountRepository;
import com.project.devbank.repositories.ClientRepository;
import com.project.devbank.repositories.StatementRepository;

@Configuration
@Transactional
public class DB implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private StatementRepository statementRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		Client c1 = new Client("ADMIN", "admin@gmail.com",  "413.880.280-00", encoder.encode("senha"));
		c1.getRoles().add("ADMIN");
		
		Client c2 = new Client("USER", "user@gmail.com", "380.299.540-69", encoder.encode("senha"));
		c2.getRoles().add("USER");
		
		
		
		Account a1 = new Account(new BigDecimal("1000"), LocalDateTime.now(), c1);
		Account a2 = new Account(new BigDecimal("1000"), LocalDateTime.now(), c2);
		
		Statement s1 = new Statement(new BigDecimal("1000"), LocalDateTime.now(), OperationType.CREDIT);
		Statement s2 = new Statement(new BigDecimal("1000"), LocalDateTime.now(), OperationType.CREDIT);
		
		a1.getStatement().add(s1);
		a2.getStatement().add(s2);
		
		accountRepository.saveAll(Arrays.asList(a1,a2));
		
		statementRepository.saveAll(Arrays.asList(s1,s2));
		
		clientRepository.saveAll(Arrays.asList(c1,c2));
		
		
		
		
		
	}

	
}
