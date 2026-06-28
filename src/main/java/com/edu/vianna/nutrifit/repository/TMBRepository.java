package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.TMB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TMBRepository extends JpaRepository<TMB, Long> {
    List<TMB> findByCliente (Cliente cliente);
    TMB findTopByClienteOrderByDataDescIdDesc(Cliente cliente);
}
