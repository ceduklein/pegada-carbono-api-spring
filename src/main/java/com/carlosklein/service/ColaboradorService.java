package com.carlosklein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carlosklein.exception.RegraNegocioException;
import com.carlosklein.model.entity.Colaborador;
import com.carlosklein.model.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository repository;
	
	public Colaborador save(Colaborador colaborador) throws RegraNegocioException {
		if (!validateName(colaborador.getNome())) {
			throw new RegraNegocioException("Nome inválido");
		}
		
		return repository.save(colaborador);
	}
	
	public List<Colaborador> list() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Optional<Colaborador> findById(Long id) {
		return repository.findById(id);
	}
	
	public Colaborador update(Colaborador colaborador) throws RegraNegocioException {
		if (!validateName(colaborador.getNome())) {
			throw new RegraNegocioException("Nome inválido");
		}
		
		return repository.save(colaborador);
	}
	
	public void delete(Colaborador colaborador) {
		repository.delete(colaborador);
	}
	
	private boolean validateName(String name) {
		if (name == null || name.length() < 3)
			return false;
		else
			return true;
	}
}
