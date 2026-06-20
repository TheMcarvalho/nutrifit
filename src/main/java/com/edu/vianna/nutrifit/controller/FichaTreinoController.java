package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.models.FichaTreino;
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

    @GetMapping("/listar")
    public String listarFichaTreino(Model model) {
        model.addAttribute("listarFichas", fichaTreinoServ.getTodasAsFichasCliente(null));
        return "ficha";
    }

    @GetMapping("/ver")
    public String verFichaTreino(FichaTreino fichaTreino, Model model) {
        Optional<FichaTreino> ficha = fichaTreinoServ.getFichaPorId(fichaTreino.getId());
        model.addAttribute("ficha", ficha.get());
        return "fichaDetalhe";
    }

    @PostMapping("/salvar")
    public String salvarFichaTreino(FichaTreino fichaTreino) {
        fichaTreinoServ.salvarFichaTreino(fichaTreino);
        return "redirect:/fichaTreino/listar";
    }
    @PostMapping("/deletar")
    public String deletarFichaTreino(FichaTreino fichaTreino) {
        fichaTreinoServ.deletarFichaTreino(fichaTreino.getId());
        return "redirect:/fichaTreino/listar";
    }

}
