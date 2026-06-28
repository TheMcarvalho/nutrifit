package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.models.Refeicao;
import com.edu.vianna.nutrifit.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/refeicao")
public class RefeicaoController {
    @Autowired
    RefeicaoService refeicaoServ;

    @GetMapping("/novo")
    public String novaRefeicao() {
        return "refeicaoNova";
    }

    @GetMapping("/listar")
    public String listarRefeicaoPorCliente(Model model) {
        model.addAttribute("listarRefeicoes", refeicaoServ.getTodosAsRefeicoes());
        return "refeicao";
    }

    @PostMapping("/salvar")
    public String salvarRefeicao(Refeicao refeicao){
        refeicaoServ.salvarRefeicao(refeicao);
        return "redirect:/refeicao/listar";
    }

    @PostMapping("/deletar")
    public String deletarRefeicao(Refeicao refeicao) {
        refeicaoServ.deletarRefeicao(refeicao.getId());
        return "redirect:/refeicao/listar";
    }

}
