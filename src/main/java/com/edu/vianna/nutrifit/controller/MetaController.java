package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.models.Meta;
import com.edu.vianna.nutrifit.service.ClienteService;
import com.edu.vianna.nutrifit.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meta")
public class MetaController {
    @Autowired
    MetaService metaServ;
    @Autowired
    ClienteService clienteServ;

    @GetMapping("/novo")
    public String novaMeta(Model model) {
        model.addAttribute("clientes", clienteServ.getTodosClientes());
        return "metaNova";
    }
    @GetMapping("/listar")
    public String listarMeta(Model model) {
        model.addAttribute("listarMeta", metaServ.getTodosAsMetas());
        return "meta";
    }
    @PostMapping("/salvar")
    public String salvarMeta(Meta meta) {
        meta.setConcluido(false);
        metaServ.salvarMeta(meta);
        return "redirect:/meta/listar";
    }

    @PostMapping("/deletar")
    public String deletarMeta(Meta meta){
        metaServ.deletarMeta(meta.getId());
        return "redirect:/meta/listar";
    }

    @PostMapping("/concluir")
    public String concluirMeta(Meta meta) {
        metaServ.marcarComoConcluida(meta.getId());
        return "redirect:/meta/listar";
    }
}
