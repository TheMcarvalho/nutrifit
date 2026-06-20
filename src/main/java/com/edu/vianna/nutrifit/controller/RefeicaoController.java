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

    @GetMapping("/listar")
    public String listarPorCliente(Model model) {
        model.addAttribute("listarRefeicoes", refeicaoServ.getRefeicaoPorCliente(null));
        return "refeicao";
    }

    @PostMapping("/salvar")
    public String salvar(Refeicao refeicao){
        refeicaoServ.salvarRefeicao(refeicao);
        return "redirect:/refeicao/listar";
    }

    @PostMapping("/deletar")
    public String deletar(Refeicao refeicao) {
        refeicaoServ.deletarRefeicao(refeicao.getId());
        return "redirect:/refeicao/listar";
    }

}
