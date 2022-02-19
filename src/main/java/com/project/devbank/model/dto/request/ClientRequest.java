package com.project.devbank.model.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientRequest {

	private Integer id;	
	@NotEmpty(message = "O campo nome deve ser Preenchido!")
	private String name;
	@NotEmpty(message = "O campo email deve ser preenchido!")
	private String email;
	@NotEmpty(message = "O campo CPF deve ser preenchido!")
	@CPF
	private String cpf;
	@NotEmpty(message = "O campo senha deve ser preenchido!")
	private String password;
	private List<String> roles = new ArrayList<>();
}
