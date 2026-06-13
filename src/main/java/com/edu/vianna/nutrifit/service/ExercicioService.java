package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.Exercicio;
import com.edu.vianna.nutrifit.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {
    @Autowired
    ExercicioRepository exercicioRep;

    public  Exercicio getExercicioPorNome(String nome){
        return exercicioRep.findByNome(nome);
    }
    public List<Exercicio> getTodosExercicios(){
        return exercicioRep.findAll();
    }
    public Exercicio salvarExercicio(Exercicio exercicio){
        return exercicioRep.save(exercicio);
    }

    public void deletarExercicio(Long id){
        exercicioRep.deleteById(id);
    }

}
