package com.carlosklein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carlosklein.exception.RegraNegocioException;
import com.carlosklein.model.entity.Chamado;
import com.carlosklein.model.repository.ChamadoRepository;
import com.carlosklein.model.repository.VeiculoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public Chamado criarChamado(Chamado chamado) throws RegraNegocioException {
		if (chamado.getDataInicio() == null) 
			throw new RegraNegocioException("Erro: Data de início inválida.");
		
		if (chamado.getEndereco() == null || chamado.getEndereco().length() < 5) 
			throw new RegraNegocioException("Erro: Endereço inválido.");
		
		if (chamado.getDistancia() < 1)
			throw new RegraNegocioException("Erro: Distância inválida.");
		
		if (!chamado.getColaborador().isHabilitado()) 
			throw new RegraNegocioException("Erro: Colaborador não habilitado.");
		
		if (!chamado.getVeiculo().isDisponivel()) 
			throw new RegraNegocioException("Erro: Veículo não disponível.");
		
		chamado.getVeiculo().setDisponivel(false);
		Chamado savedChamado =  repository.save(chamado);
		veiculoRepository.save(chamado.getVeiculo());
		
		return savedChamado;
	}
	
	public void atualizar(Chamado chamado) throws RegraNegocioException {
		if (chamado.getEndereco() == null || chamado.getEndereco().length() < 5)
			throw new RegraNegocioException("Erro: Endereço inválido."); 
		
		if (chamado.getDistancia() < 1)
			throw new RegraNegocioException("Erro: Distância inválida.");
		
		repository.save(chamado);
	}
	
	public void encerrarChamado(Chamado chamado) {
		chamado.getVeiculo().setDisponivel(true);
		chamado.setConcluido(true);
		repository.save(chamado);
		veiculoRepository.save(chamado.getVeiculo());
	}
	
	public void excluir(Chamado chamado) {
		chamado.getVeiculo().setDisponivel(true);
		veiculoRepository.save(chamado.getVeiculo());
		repository.delete(chamado);
	}
	
	public List<Chamado> listar() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Optional<Chamado> findById(Long id) {
		return repository.findById(id);
	}
	
}
