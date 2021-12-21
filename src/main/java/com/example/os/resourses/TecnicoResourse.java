package com.example.os.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.os.domain.Tecnico;
import com.example.os.services.TecnicoService;

@RestController
@RequestMapping(value ="/tecnicos")   // estamos setando um endpoint inicial para podermos acessar os recursos do nosso técnico
public class TecnicoResourse {			// localhost:8080/tecnicos/1

	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);              //no corpo da nossa resposta eu vou passar o objeto 
	}
}
