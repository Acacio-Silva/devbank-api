package com.project.devbank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Account implements Serializable {

	private static final long serialVersionUID = 1743542186042767998L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	@NonNull
	private BigDecimal saldo;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Statement> statement = new ArrayList<>();
	@NonNull
	private LocalDateTime moment;
	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	private Client client;
	
	
}
