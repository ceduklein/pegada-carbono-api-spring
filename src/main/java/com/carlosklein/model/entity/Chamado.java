package com.carlosklein.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "chamado")
public class Chamado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "data")
	private LocalDate dataInicio;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "distancia")
	private Double distancia;
	
	@Column(name = "carbono")
	private Double pegadaCarbono;
	
	@Column(name = "concluido")
	private boolean concluido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colaborador", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Colaborador colaborador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_veiculo", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Veiculo veiculo;
		
	public Chamado(LocalDate dataInicio, String endereco, Double distancia, Colaborador colaborador, Veiculo veiculo) {
				this.dataInicio = dataInicio;
				this.endereco = endereco;
				this.distancia = distancia;
				this.colaborador = colaborador;
				this.veiculo = veiculo;
				this.concluido = false;
				calcularPegadaCarbono();
	}
	
	public Chamado() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public boolean isConcluido() {
		return concluido;
	}
	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public Double getPegadaCarbono() {
		return pegadaCarbono;
	}
	public void setPegadaCarbono(Double pegadaCarbono) {
		this.pegadaCarbono = pegadaCarbono;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public void calcularPegadaCarbono() {
		Double consumo = distancia / veiculo.getKmLitro();
		this.pegadaCarbono = consumo * 0.82 * 0.75 * 3.7;
	}
}
