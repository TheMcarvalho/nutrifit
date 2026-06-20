package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    ClienteService clienteServ;
    @Autowired
    ExercicioService exercicioServ;
    @Autowired
    FichaTreinoService fichaTreinoServ;
    @Autowired
    MetaService metaServ;
    @Autowired
    RefeicaoService refeicaoServ;
    @Autowired
    TMBService tmbServ;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalClientes",clienteServ.getTodosClientes().size());
        model.addAttribute("totalExercicios", exercicioServ.getTodosExercicios().size());
        model.addAttribute("totalFichas", fichaTreinoServ.getTodasAsFichasCliente(null).size());
        model.addAttribute("todasMetas", metaServ.getMetasCliente(null));
        model.addAttribute("metasPendentes", metaServ.getMetasPorStatus(null, false));
        model.addAttribute("totalCalorias", refeicaoServ.getTotalCaloriasDiarias(null));
        model.addAttribute("tmbRecente", tmbServ.getTMBPorCliente(null));
        return "dashboard";
    }
}
