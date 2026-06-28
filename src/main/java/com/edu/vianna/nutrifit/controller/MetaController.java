package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.config.DTO.UserLogadoDTO;
import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.Meta;
import com.edu.vianna.nutrifit.service.ClienteService;
import com.edu.vianna.nutrifit.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String novaMeta(Model model, Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            model.addAttribute("clientes", clienteServ.getTodosClientes());
        }
        return "metaNova";
    }

    @GetMapping("/listar")
    public String listarMeta(Model model, Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            model.addAttribute("listarMeta", metaServ.getTodosAsMetas());
        } else {
            long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
            Cliente cliente = clienteServ.getCliente(idUsuario);
            model.addAttribute("listarMeta", metaServ.getMetasCliente(cliente));
        }
        return "meta";
    }

    @PostMapping("/salvar")
    public String salvarMeta(Meta meta, Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CLIENTE"))) {
            long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
            Cliente cliente = clienteServ.getCliente(idUsuario);
            meta.setCliente(cliente);
        }
        meta.setConcluido(false);
        metaServ.salvarMeta(meta);
        return "redirect:/meta/listar";
    }

    @PostMapping("/deletar")
    public String deletarMeta(Meta meta) {
        metaServ.deletarMeta(meta.getId());
        return "redirect:/meta/listar";
    }

    @PostMapping("/concluir")
    public String concluirMeta(Meta meta) {
        metaServ.marcarComoConcluida(meta.getId());
        return "redirect:/meta/listar";
    }
}
