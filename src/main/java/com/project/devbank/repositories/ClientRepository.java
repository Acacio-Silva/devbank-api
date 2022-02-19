package com.project.devbank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.devbank.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findByCpf(String cpf);
	Optional<Client> findByEmail(String email);
}
