package com.carlosklein.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosklein.model.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
