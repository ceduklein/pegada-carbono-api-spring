package com.carlosklein.controller.dto;

import java.time.LocalDate;

public class ChamadoDTO {

	private Long id;
	private LocalDate dataInicio;
	private String endereco;
	private Double distancia;
	private Long colaborador;
	private Long veiculo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
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
	public Long getColaborador() {
		return colaborador;
	}
	public void setColaborador(Long colaborador) {
		this.colaborador = colaborador;
	}
	public Long getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Long veiculo) {
		this.veiculo = veiculo;
	}
	
	
}
