package com.example.os.resourses;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.os.dtos.OsDTO;
import com.example.os.services.OsService;



@CrossOrigin("*")	// Informando que a API pode receber requisições de multiplas fontes
@RestController
@RequestMapping(value = "/os")
public class OsResourse {

	@Autowired
	private OsService service;
	
	// BUSCA POR ID
	@GetMapping(value = "/{id}")	// GET localhost:8080/os/id
	public ResponseEntity<OsDTO> findById(@PathVariable Integer id) {
		OsDTO obj = new OsDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	// BUSCAR TODOS
	@GetMapping		// GET localhost:8080/os/
	public ResponseEntity<List<OsDTO>> findAll() {
		List<OsDTO> list = service.findAll().stream().map(obj -> new OsDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	// CRIAR OS	
	@PostMapping		// POST localhost:8080/os
	public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO obj) {
		obj = new OsDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	// ATUALIZANDO OS
	@PutMapping		// PUT localhost:8080/os
	public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO obj) {
		obj = new OsDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
	
}


