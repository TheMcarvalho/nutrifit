package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Exercicio;
import com.edu.vianna.nutrifit.models.FichaTreino;
import com.edu.vianna.nutrifit.models.ItensFicha;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItensFichaRepository extends JpaRepository <ItensFicha, Long> {
    List<ItensFicha> findByExercicio (Exercicio exercicio);
    List<ItensFicha> findByFichaTreino(FichaTreino fichaTreino);
}
