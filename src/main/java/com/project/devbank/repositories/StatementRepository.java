package com.project.devbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.devbank.model.Statement;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Integer>{

}
