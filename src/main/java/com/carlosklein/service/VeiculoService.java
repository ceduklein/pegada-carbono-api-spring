package com.carlosklein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosklein.exception.RegraNegocioException;
import com.carlosklein.model.entity.Veiculo;
import com.carlosklein.model.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;
	
	public Veiculo save(Veiculo veiculo) throws RegraNegocioException {
		if (!validarModelo(veiculo.getModelo())) {
			throw new RegraNegocioException("Erro: Modelo inválido.");
		}
		
		if (!validarPlaca(veiculo.getPlaca())) {
			throw new RegraNegocioException("Erro: Placa inválida.");
		}
		
		if (!validarConsumo(veiculo.getKmLitro())) {
			throw new RegraNegocioException("Erro: Consumo inválido");
		}
		
		return repository.save(veiculo);
	}
	
	public Veiculo update(Veiculo veiculo) throws RegraNegocioException {
		if (!validarModelo(veiculo.getModelo())) {
			throw new RegraNegocioException("Erro: Modelo inválido.");
		}
		
		if (!validarPlaca(veiculo.getPlaca())) {
			throw new RegraNegocioException("Erro: Placa inválida.");
		}
		
		if (!validarConsumo(veiculo.getKmLitro())) {
			throw new RegraNegocioException("Erro: Consumo inválido");
		}
		
		return repository.save(veiculo);
	}
	
	
	public void delete(Veiculo veiculo) {
		repository.delete(veiculo);
	}
	
	public List<Veiculo> list() {
		return repository.findAll();
	}
	
	public Optional<Veiculo> findById(Long id) {
		return repository.findById(id);
	}
	
	private boolean validarModelo(String modelo) {
		if (modelo == null || modelo.length() < 2)
			return false;
		else
			return true;
	}
	
	private boolean validarPlaca(String placa) {
		if (placa == null || placa.length() < 7)
			return false;
		else
			return true;
	}
	
	private boolean validarConsumo(Double consumo) {
		if (consumo < 1.00 || consumo > 50)
			return false;
		else
			return true;
	}
}
