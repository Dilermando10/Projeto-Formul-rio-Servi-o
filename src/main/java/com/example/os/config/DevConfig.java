package com.example.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.os.services.DBservice;

@Configuration
@Profile("dev") // aplication.proprieties vai identificar que aqui tem um perfil e vamos settar
				// um nome pra ele
public class DevConfig {

	@Autowired
	private DBservice dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Bean
	public boolean intanciaDB() {
		
		if (ddl.equals("create")) {
			this.dbService.intanciaDB();
		}
		return false;
	}
}
