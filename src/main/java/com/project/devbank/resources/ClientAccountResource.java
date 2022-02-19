package com.project.devbank.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.devbank.model.dto.Transfer;
import com.project.devbank.model.dto.request.AccountRequest;
import com.project.devbank.model.dto.request.ClientRequest;
import com.project.devbank.model.dto.request.UserLogin;
import com.project.devbank.service.impl.ClientAccountServiceImpl;

@RestController()
@RequestMapping(value = "/user")
public class ClientAccountResource {
	
	
	
	@Autowired
	private ClientAccountServiceImpl clientAccountServiceImpl;
	
	@PostMapping(value = "/signin")
	public ResponseEntity<AccountRequest> openAccount(@RequestBody ClientRequest clientRequest){
			AccountRequest obj = clientAccountServiceImpl.openAccount(clientRequest);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<UserLogin> login(@RequestBody UserLogin login){
		return ResponseEntity.ok().body(clientAccountServiceImpl.login(login));
	}
	
	@PutMapping(value = "/transfer")
	public ResponseEntity<AccountRequest> transfer(@RequestBody Transfer transfer, Authentication authentication){
		return ResponseEntity.ok().body(clientAccountServiceImpl.transfer(transfer.getNumber(), transfer.getValue(), authentication));
	}
	

}
