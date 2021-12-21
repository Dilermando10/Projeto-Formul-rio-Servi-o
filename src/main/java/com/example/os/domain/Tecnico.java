package com.example.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1l;
	
	@JsonIgnore
	@OneToMany(mappedBy ="tecnico")    // 1 para muitos (1 Tecnico para muitas ordens de serviço"OS") mappedBy = ele vai informar que la na classe "os" ele foi mapeado pelo atributo técnico
	private List <OS> list = new ArrayList<>();
	
	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

	public Tecnico() {
		super();

	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);

	}

}
