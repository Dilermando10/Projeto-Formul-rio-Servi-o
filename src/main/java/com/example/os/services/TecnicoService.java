package com.example.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.os.domain.Pessoa;
import com.example.os.domain.Tecnico;
import com.example.os.dtos.TecnicoDTO;
import com.example.os.repositories.PessoaRepository;
import com.example.os.repositories.TecnicoRepository;
import com.example.os.services.exceptions.DataIntegratyViolationException;
import com.example.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID !" + id + ", tipo: " + Tecnico.class.getName()));

	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		if (findBYCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados !");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));

	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObjt = findById(id);

		if (findBYCPF(objDTO) != null && findBYCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados !");
		}
		oldObjt.setNome(objDTO.getNome());
		oldObjt.setCpf(objDTO.getNome());
		oldObjt.setTelefone(objDTO.getNome());
		return repository.save(oldObjt);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException(
					" Técnico possui ordem de serviço, técnico não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private Pessoa findBYCPF(TecnicoDTO objDTO) {
		Pessoa obj =  pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}