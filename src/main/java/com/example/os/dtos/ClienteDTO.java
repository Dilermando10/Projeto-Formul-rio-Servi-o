package com.example.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.example.os.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1l;
	private Integer id;
	
	@NotEmpty(message = " o campo Nome é Requerido")
	private String nome;
	
	@NotEmpty(message = " o campo CPF é Requerido")
	@CPF
	private String cpf;
	
	@NotEmpty(message = " o campo TELEFONE é Requerido")
	private String telefone;

	public ClienteDTO() {
		super();

	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
