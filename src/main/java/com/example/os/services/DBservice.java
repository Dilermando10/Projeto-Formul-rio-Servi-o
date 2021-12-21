package com.example.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.os.domain.Cliente;
import com.example.os.domain.OS;
import com.example.os.domain.Tecnico;
import com.example.os.domain.enuns.Prioridade;
import com.example.os.domain.enuns.Status;
import com.example.os.repositories.ClienteRepository;
import com.example.os.repositories.OSRepository;
import com.example.os.repositories.TecnicoRepository;


@Service // informar que é um serviço e que ai poderemos injetar instancias em da nossa
public class DBservice {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	
	public void intanciaDB() {
		Tecnico t1 = new Tecnico(null, "Dilermando", "043.304.620-10", "(34)98484-8484");
		Cliente c1 = new Cliente(null, "Betina Campos", "184.421.510-52", "(34)98485-8484");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1); // o tecnico 1 "T1" vai chamar a lista de Ordem de serviços "OS" e adicionar
								// nessa lista a ordem de servico "os1"
		c1.getList().add(os1); // o cliente 1 "c1" vai chamar a lista de Ordem de serviços "OS" e adicionar
								// nessa lista a ordem de servico "os1"

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
