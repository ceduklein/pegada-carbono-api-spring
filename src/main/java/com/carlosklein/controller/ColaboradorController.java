package com.carlosklein.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.tool.hbm2ddl.SchemaUpdateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosklein.model.entity.Colaborador;
import com.carlosklein.model.repository.ColaboradorRepository;
import com.carlosklein.service.ColaboradorService;

@CrossOrigin()
@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

	@Autowired
	ColaboradorService colaboradorService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Colaborador c) {
		Colaborador colaborador =  new Colaborador();
		colaborador.setNome(c.getNome());
		colaborador.setHabilitado(c.isHabilitado());
		
		try {
			Colaborador colab = colaboradorService.save(colaborador);
			return new ResponseEntity<>(colab, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity list() {
		List<Colaborador> colaboradores = colaboradorService.list();
		return ResponseEntity.ok(colaboradores);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<Colaborador> colaborador = colaboradorService.findById(id);
		
		if (!colaborador.isPresent())
			return ResponseEntity.badRequest().body("Colaborador não encontrado.");
		else
			return ResponseEntity.ok(colaborador);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Colaborador colaborador) {
		Optional<Colaborador> colab = colaboradorService.findById(id);
		
		if (!colab.isPresent()) {
			return ResponseEntity.badRequest().body("Colaborador não encontrado.");
		}
		
		Colaborador updatedColab = new Colaborador();
		updatedColab.setId(colab.get().getId());
		updatedColab.setNome(colaborador.getNome());
		updatedColab.setHabilitado(colaborador.isHabilitado());
		
		try {
			colaboradorService.update(updatedColab);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Optional<Colaborador> colaborador = colaboradorService.findById(id);
		
		if (!colaborador.isPresent()) {
			return ResponseEntity.badRequest().body("Colaborador não encontrado para o id informado.");
		}
		
		try {
			colaboradorService.delete(colaborador.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
