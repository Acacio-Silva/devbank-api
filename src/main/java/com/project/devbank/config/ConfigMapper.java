package com.project.devbank.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMapper {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
