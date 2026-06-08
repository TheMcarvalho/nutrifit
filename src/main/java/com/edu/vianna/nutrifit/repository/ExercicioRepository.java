package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio,Long> {
    Exercicio findByNome(String nome);
}
