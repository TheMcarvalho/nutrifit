package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.enums.EGenero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByNome(String nome);
    List<Cliente> findByGenero(EGenero genero);
    List<Cliente> findByIdadeBetween(int idadeMin, int idadeMax);
}
