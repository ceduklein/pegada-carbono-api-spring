package com.carlosklein.controller;


import java.util.List;
import java.util.Optional;

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

import com.carlosklein.model.entity.Veiculo;
import com.carlosklein.service.VeiculoService;

@CrossOrigin()
@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

	@Autowired
	VeiculoService veiculoService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Veiculo v) {
		Veiculo veiculo = new Veiculo();
		veiculo.setModelo(v.getModelo());
		veiculo.setPlaca(v.getPlaca());
		veiculo.setKmLitro(v.getKmLitro());
		
		try {
			Veiculo savedVeiculo = veiculoService.save(veiculo);
			return new ResponseEntity<>(savedVeiculo, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Veiculo veiculo) {
		Optional<Veiculo> v = veiculoService.findById(id);
		
		if (!v.isPresent()) {
			return ResponseEntity.badRequest().body("Veículo não encontrado.");
		}
		
		Veiculo updatedVeiculo = new Veiculo();
		updatedVeiculo.setId(v.get().getId());
		updatedVeiculo.setDisponivel(veiculo.isDisponivel());
		updatedVeiculo.setModelo(veiculo.getModelo());
		updatedVeiculo.setPlaca(veiculo.getPlaca());
		updatedVeiculo.setKmLitro(veiculo.getKmLitro());
		
		try {
			veiculoService.update(updatedVeiculo);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Optional<Veiculo> v = veiculoService.findById(id);
		
		if (!v.isPresent())
			return ResponseEntity.badRequest().body("Veículo não encontrado.");
		
		try {
			veiculoService.delete(v.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity list() {
		List<Veiculo> veiculos = veiculoService.list();
		return ResponseEntity.ok(veiculos);
	}
	
	@GetMapping("{id}")
	public ResponseEntity findById(@PathVariable("id") Long id) {
		Optional<Veiculo> v = veiculoService.findById(id);
		
		if (!v.isPresent())
			return ResponseEntity.badRequest().body("Veículo não encontrado.");
		else
			return ResponseEntity.ok(v);
	}
	
	
}
