package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.models.Exercicio;
import com.edu.vianna.nutrifit.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercicio")
public class ExercicioController {
    @Autowired
    ExercicioService exercicioServ;

    @GetMapping("/listar")
    public String listarExercicio(Model model) {
        model.addAttribute("listarExercicios",exercicioServ.getTodosExercicios());
        return "exercicio";
    }

    @PostMapping("/salvar")
    public String salvarExercicio(Exercicio exercicio) {
        exercicioServ.salvarExercicio(exercicio);
        return "redirect:/exercicio/listar";
    }

    @PostMapping("/deletar")
    public String deletarExercicio(Exercicio exercicio) {
        exercicioServ.deletarExercicio(exercicio.getId());
        return "redirect:/exercicio/listar";
    }
}
