package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.FichaTreino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichaTreinoRepository extends JpaRepository<FichaTreino,Long> {
    FichaTreino findByNome(String nome);
    FichaTreino findByClienteAndNome(Cliente cliente, String nome);
    List<FichaTreino> findByCliente(Cliente cliente);
}
