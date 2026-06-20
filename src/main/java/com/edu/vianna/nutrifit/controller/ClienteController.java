package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteServ;

    @GetMapping("/listar")
    public String listarCliente(Model model) {
        model.addAttribute("listarClientes",clienteServ.getTodosClientes());
        return "clientes";
    }

    @GetMapping("/novo")
    public String novoCliente() {
        return "clienteNovo";
    }

    @PostMapping("/salvar")
    public String salvarCliente(Cliente cliente) {
        clienteServ.salvarCliente(cliente);
        return "redirect:/cliente/listar";
    }
    @PostMapping("/deletar")
    public String deletarCliente(Cliente cliente) {
        clienteServ.deletarCliente(cliente.getId());
        return "redirect:/cliente/listar";
    }

}
