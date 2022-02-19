package com.project.devbank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Client implements Serializable{
	
	private static final long serialVersionUID = -9093322746649673436L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String cpf;
	@NonNull
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	  @CollectionTable(name = "tab_client_roles", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "role_id") 
	@NonNull
	private List<String> roles = new ArrayList<>();

}
