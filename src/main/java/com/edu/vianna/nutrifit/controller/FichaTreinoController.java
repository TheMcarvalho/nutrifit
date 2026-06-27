package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.models.Exercicio;
import com.edu.vianna.nutrifit.models.FichaTreino;
import com.edu.vianna.nutrifit.service.ClienteService;
import com.edu.vianna.nutrifit.service.ExercicioService;
import com.edu.vianna.nutrifit.service.FichaTreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/fichaTreino")
public class FichaTreinoController {
    @Autowired
    FichaTreinoService fichaTreinoServ;
    @Autowired
    ClienteService clienteServ;
    @Autowired
    ExercicioService exercicioServ;

    @GetMapping("/listar")
    public String listar(Model model) {
        //model.addAttribute("listarFichas", fichaTreinoServ.getTodasAsFichasCliente(null));
        model.addAttribute("listarFichas", fichaTreinoServ.getTodosAsFichas());
        return "ficha";
    }

    @GetMapping("/ver")
    public String ver(FichaTreino fichaTreino, Model model) {
        Optional<FichaTreino> ficha = fichaTreinoServ.getFichaPorId(fichaTreino.getId());
        model.addAttribute("ficha", ficha.get());
        return "fichaDetalhe";
    }

    @GetMapping("/novo")
    public String novaFicha(Model model) {
        model.addAttribute("clientes", clienteServ.getTodosClientes());
        model.addAttribute("exercicios", exercicioServ.getTodosExercicios());
        return "fichaNova";
    }

    @PostMapping("/salvar")
    public String salvar(FichaTreino fichaTreino) {
        fichaTreinoServ.salvarFichaTreino(fichaTreino);
        return "redirect:/fichaTreino/listar";
    }
    @PostMapping("/deletar")
    public String deletar(FichaTreino fichaTreino) {
        fichaTreinoServ.deletarFichaTreino(fichaTreino.getId());
        return "redirect:/fichaTreino/listar";
    }

}
