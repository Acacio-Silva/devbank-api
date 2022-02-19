package com.project.devbank.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLogin {

	private String cpf;
	private String password;
}
