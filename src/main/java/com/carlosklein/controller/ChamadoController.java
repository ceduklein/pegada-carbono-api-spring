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

import com.carlosklein.controller.dto.ChamadoDTO;
import com.carlosklein.model.entity.Chamado;
import com.carlosklein.model.entity.Colaborador;
import com.carlosklein.model.entity.Veiculo;
import com.carlosklein.service.ChamadoService;
import com.carlosklein.service.ColaboradorService;
import com.carlosklein.service.VeiculoService;

@CrossOrigin
@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@PostMapping
	public ResponseEntity<?> save (@RequestBody ChamadoDTO dto) {
		try {
			Chamado chamado = dtoConverter(dto);
			chamado = chamadoService.criarChamado(chamado);
			return new ResponseEntity<>(chamado, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<Chamado> chamados = chamadoService.listar();
		return ResponseEntity.ok(chamados);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<Chamado> c = chamadoService.findById(id);
		if (!c.isPresent())
			return ResponseEntity.badRequest().body("Chamado não encontrado.");
		else
			return ResponseEntity.ok(c);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ChamadoDTO dto) {
		Optional<Chamado> c = chamadoService.findById(id);
		if (!c.isPresent())
			return ResponseEntity.badRequest().body("Chamado não encontrado.");
		
		c.get().setEndereco(dto.getEndereco());
		c.get().setDistancia(dto.getDistancia());
		
		try {
			chamadoService.atualizar(c.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Optional<Chamado> c = chamadoService.findById(id);
		if (!c.isPresent())
			return ResponseEntity.badRequest().body("Chamado não encontrado.");
		
		chamadoService.excluir(c.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
	
	@PutMapping("/encerrar/{id}")
	public ResponseEntity<?> encerrar(@PathVariable("id") Long id) {
		Optional<Chamado> c = chamadoService.findById(id);
		if (!c.isPresent())
			return ResponseEntity.badRequest().body("Chamado não encontrado.");
		
		chamadoService.encerrarChamado(c.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
	
	
	private Chamado dtoConverter(ChamadoDTO dto) {
		Optional<Veiculo> veiculo = veiculoService.findById(dto.getVeiculo());
		Optional<Colaborador> colab = colaboradorService.findById(dto.getColaborador());
		
		Chamado c = new Chamado();
		c.setDataInicio(dto.getDataInicio());
		c.setEndereco(dto.getEndereco());
		c.setDistancia(dto.getDistancia());
		c.setVeiculo(veiculo.get());
		c.setColaborador(colab.get());
		c.calcularPegadaCarbono();
		return c;	
	}
	
}
