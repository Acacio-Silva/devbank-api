package com.project.devbank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.devbank.model.Account;
import com.project.devbank.model.Client;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByClient(Client client);
}
