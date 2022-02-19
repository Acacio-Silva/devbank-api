package com.project.devbank.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.devbank.model.dto.AccountById;
import com.project.devbank.model.dto.AccountDTO;
import com.project.devbank.model.dto.AccountOperations;
import com.project.devbank.model.dto.Transfer;
import com.project.devbank.model.dto.request.AccountRequest;
import com.project.devbank.model.dto.request.ClientRequest;
import com.project.devbank.service.impl.AccountServiceImpl;


@RestController
@RequestMapping(value = "/accounts")
public class AccountResource {

	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> findAll(){
		return ResponseEntity.ok().body(accountServiceImpl.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountById> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(accountServiceImpl.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<AccountRequest> openAccount(@Valid @RequestBody ClientRequest clientRequest){
		AccountRequest accountRequest = accountServiceImpl.openAccount(clientRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accountRequest.getId()).toUri();
		return ResponseEntity.created(uri).body(accountRequest);
	}
	@PutMapping(value = "/transfer/{id}")
	public ResponseEntity<AccountRequest> transfer(@PathVariable Integer id, @RequestBody Transfer transfer ){
		return ResponseEntity.ok().body(accountServiceImpl.transfer(id, transfer.getNumber(), transfer.getValue()));	
	}
	
	@PutMapping(value = "/credit/{id}")
	public ResponseEntity<AccountRequest> credit(@PathVariable Integer id, @RequestBody AccountOperations accountOperations ){
		return ResponseEntity.ok().body(accountServiceImpl.credit(id, accountOperations.getValue()));	
	}
	
	@PutMapping(value = "/debit/{id}")
	public ResponseEntity<AccountRequest> debit(@PathVariable Integer id, @RequestBody AccountOperations accountOperations ){
		return ResponseEntity.ok().body(accountServiceImpl.debit(id, accountOperations.getValue()));	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AccountRequest> deleteById(@PathVariable Integer id){
		accountServiceImpl.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
