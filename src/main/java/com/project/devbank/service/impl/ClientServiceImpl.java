package com.project.devbank.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.devbank.model.Client;
import com.project.devbank.model.dto.ClientAllDTO;
import com.project.devbank.model.dto.request.ClientRequest;
import com.project.devbank.repositories.ClientRepository;
import com.project.devbank.service.ClientService;
import com.project.devbank.service.exceptions.ObjectNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public List<ClientAllDTO> findAll() {
		List<Client> clients = clientRepository.findAll();
		List<ClientAllDTO> list = clients.stream().map(x -> mapper.map(x, ClientAllDTO.class)).collect(Collectors.toList()); 
		return list;
	}


	@Override
	public ClientRequest findById(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		if(client.isPresent()) {
			return mapper.map(client.get(), ClientRequest.class);
		}
		throw new ObjectNotFoundException("Cliente não encontrado com ID: " + id);
	}


	@Override
	public ClientRequest findByCpf(String cpf) {
		Optional<Client> client = clientRepository.findByCpf(cpf);
		if(client.isPresent()) {
			return mapper.map(client.get(), ClientRequest.class);
		}
		throw new ObjectNotFoundException("Cliente não encontrado com CPF: " + cpf);
	}


	@Override
	public ClientRequest save(ClientRequest clientRequest) {
		Client client = clientRepository.save(mapper.map(clientRequest, Client.class));
		return mapper.map(client, ClientRequest.class);
	}
	
	@Override
	public ClientRequest update(Integer id, ClientRequest clientRequest) {
		Optional<Client> client = clientRepository.findById(id);
		if(client.isPresent()) {
		client.get().setId(id);
		client.get().setName(clientRequest.getName());
		client.get().setEmail(clientRequest.getEmail());;
		client.get().setPassword(encoder.encode(clientRequest.getPassword()));
		
			}		
		
		clientRepository.save(mapper.map(client.get(), Client.class));
		
		return mapper.map(client.get(), ClientRequest.class);
	}


	@Override
	public ClientRequest remove(Integer id) {
		ClientRequest clientRequest = findById(id);
		clientRepository.delete(mapper.map(clientRequest, Client.class));
		return clientRequest;
	}

}
