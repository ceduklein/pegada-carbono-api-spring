package com.carlosklein.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosklein.model.entity.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
