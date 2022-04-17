package com.carlosklein.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "colaborador")
public class Colaborador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "idcolaborador")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "habilitado")
	private boolean habilitado;
	
	public Colaborador(String nome) {
		this.nome = nome;
	}
	
	public Colaborador() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public String toString() {
		return "Id: " + id + " - " + nome; 
	}
}