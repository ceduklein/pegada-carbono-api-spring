package com.carlosklein.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosklein.model.entity.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

}
