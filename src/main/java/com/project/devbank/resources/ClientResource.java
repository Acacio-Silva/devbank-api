package com.project.devbank.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.devbank.model.dto.ClientAllDTO;
import com.project.devbank.model.dto.request.ClientRequest;
import com.project.devbank.service.impl.ClientServiceImpl;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientServiceImpl clientServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<ClientAllDTO>> findAll() {
		return ResponseEntity.ok().body(clientServiceImpl.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientRequest> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(clientServiceImpl.findById(id));
	}
	
	@GetMapping(value = "/search/{cpf}")
	public ResponseEntity<ClientRequest> findByCpf(@PathVariable String cpf){
		return ResponseEntity.ok().body(clientServiceImpl.findByCpf(cpf));
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientRequest> update(@PathVariable Integer id, @RequestBody ClientRequest clientRequest){
		return ResponseEntity.ok().body(clientServiceImpl.update(id, clientRequest));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientRequest> remove(@PathVariable Integer id){
		clientServiceImpl.remove(id);
		return ResponseEntity.noContent().build();
	}
	
}
