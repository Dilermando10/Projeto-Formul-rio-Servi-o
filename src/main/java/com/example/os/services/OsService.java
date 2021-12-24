package com.example.os.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.os.domain.Cliente;
import com.example.os.domain.OS;
import com.example.os.domain.Tecnico;
import com.example.os.domain.enuns.Status;
import com.example.os.dtos.OsDTO;
import com.example.os.repositories.OSRepository;
import com.example.os.services.exceptions.ObjectNotFoundException;



@Service
public class OsService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	// BUSCA POR ID
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + OS.class.getName()));
	}
	
	// BUSCAR TODOS
	public List<OS> findAll() {
		return repository.findAll();
	}
	
	// CRIAR OS
	public OS create(@Valid OsDTO obj) {
		return fromDTO(obj);
	}
	
	// ATUALIZANDO OS
	public OS update(@Valid OsDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	  
	// Instancia nova OS setando os novos valores
	private OS fromDTO(OsDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObeservacoes(obj.getObservacoes());
		newObj.setPrioridade(obj.getPrioridade());
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		// Validando se Status == ENCERRADO
		if (newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

	
	
	
	
}


