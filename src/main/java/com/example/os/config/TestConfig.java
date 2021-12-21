package com.example.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.os.services.DBservice;

@Configuration
@Profile("test") // aplication.proprieties vai identificar que aqui tem um perfil e vamos settar
					// um nome pra ele
public class TestConfig {

	@Autowired
	private DBservice dbService;

	@Bean
	public void intanciaDB() {
		this.dbService.intanciaDB();
	}
}
