package com.example.os.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.os.domain.enuns.Prioridade;
import com.example.os.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class OS {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd//MM/yyyy HH:mm") //padrão que eu vou adotar para minha data e hora
	private LocalDateTime dataAbertura; //padrão que eu vou adotar para minha data e hora
	
	@JsonFormat(pattern = "dd//MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	private String obeservacoes;
	private Integer status;
	
	@ManyToOne                          // Muitos para um 
	@JoinColumn(name ="Tecnico_id")     // seria o nome da da nossa fk chave estrangeira na tabela "OS"
	private Tecnico tecnico;			//Atributo de referencia
	
	@ManyToOne
	@JoinColumn(name ="Cliente_id")
	private Cliente cliente;
	
	public OS() {
		super();
		this.setDataAbertura (LocalDateTime.now ()); //se uma ordem de servico for instanciada e não for usado os parametros do construtor abaixo  automaticamente ja vai setar  um valor de data e horaria do momento e tambem vai setar  uma PrioridadeBAIXA e Um StatusABERTO
		this.setPrioridade(Prioridade.BAIXA); 		// isso foi feito porque uma ordem de servicos tem que ter uma prioridade e um status (ex :ou ela esta aberta ou ela esta em andamento ou esta encerrada)
		this.setStatus(Status.ABERTO);           
	}
	

	public OS(Integer id, Prioridade prioridade,
			String obeservacoes, Status status, Tecnico tecnico, Cliente cliente) {
		super();
		this.id = id;
		this.setDataAbertura (LocalDateTime.now ());      // quando ela for instanciada ele vai pegar a data e horario do momento e vai setar ao nosso atributo dataAbertura
		this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
		this.obeservacoes = obeservacoes;
		this.status = (status == null ) ? 0 : status.getCod();
		this.tecnico = tecnico;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}

	public String getObeservacoes() {
		return obeservacoes;
	}

	public void setObeservacoes(String obeservacoes) {
		this.obeservacoes = obeservacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);                   //aula minuto 9
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OS other = (OS) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
	
}
