package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.config.DTO.UserLogadoDTO;
import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.Refeicao;
import com.edu.vianna.nutrifit.service.ClienteService;
import com.edu.vianna.nutrifit.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/refeicao")
public class RefeicaoController {
    @Autowired
    RefeicaoService refeicaoServ;
    @Autowired
    ClienteService clienteServ;

    @GetMapping("/novo")
    public String novaRefeicao() {
        return "refeicaoNova";
    }

    @GetMapping("/listar")
    public String listarRefeicaoPorCliente(Model model, Authentication auth) {
        long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
        Cliente cliente = clienteServ.getCliente(idUsuario);
        model.addAttribute("listarRefeicoes", refeicaoServ.getRefeicaoPorCliente(cliente));
        return "refeicao";
    }

    @PostMapping("/salvar")
    public String salvarRefeicao(Refeicao refeicao, Authentication auth) {
        long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
        Cliente cliente = clienteServ.getCliente(idUsuario);
        refeicao.setCliente(cliente);
        refeicaoServ.salvarRefeicao(refeicao);
        return "redirect:/refeicao/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarRefeicao(@PathVariable("id") long id, Model model) {
        Refeicao refeicao = refeicaoServ.findById(id);
        if (refeicao != null) {
            model.addAttribute("refeicao", refeicao);
            return "refeicaoNova";
        }
        return "redirect:/refeicao/listar";
    }

    @PostMapping("/deletar")
    public String deletarRefeicao(Refeicao refeicao) {
        refeicaoServ.deletarRefeicao(refeicao.getId());
        return "redirect:/refeicao/listar";
    }

}
