package com.project.devbank.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class FieldMessage implements Serializable{

	private static final long serialVersionUID = -8530400587473669031L;
	private String FieldName;
	private String name;
}
