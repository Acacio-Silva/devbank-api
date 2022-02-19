package com.project.devbank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.devbank.model.dto.ClientAllDTO;
import com.project.devbank.model.dto.request.ClientRequest;


@Service
public interface ClientService {

	List<ClientAllDTO> findAll();
	ClientRequest findById(Integer id);
	ClientRequest findByCpf(String cpf);
	ClientRequest save(ClientRequest clientRequest);
	ClientRequest update(Integer id, ClientRequest clientRequest);
	ClientRequest remove(Integer id);
}

