package com.carlosklein.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idveiculo")
	private Long id;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "disponivel")
	private boolean disponivel;
	
	@Column(name = "km_litro")
	private Double kmLitro;
	
	public Veiculo(String modelo, String placa, Double kmLitro) {
		this.modelo = modelo;
		this.placa = placa;
		this.kmLitro = kmLitro;
		this.disponivel = true;
	}
	
	public Veiculo() {
		this.disponivel = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Double getKmLitro() {
		return kmLitro;
	}

	public void setKmLitro(Double kmLitro) {
		this.kmLitro = kmLitro;
	}
	
	public String toString() {
		return modelo + " - Placa: " + placa;
	}
}
