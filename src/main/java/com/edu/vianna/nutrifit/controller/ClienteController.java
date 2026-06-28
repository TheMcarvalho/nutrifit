package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.config.DTO.UserLogadoDTO;
import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

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
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        clienteServ.salvarCliente(cliente);
        return "redirect:/cliente/listar";
    }
    @PostMapping("/deletar")
    public String deletarCliente(Cliente cliente) {
        clienteServ.deletarCliente(cliente.getId());
        return "redirect:/cliente/listar";
    }

    @GetMapping("/perfil")
    public String perfil(Model model, Authentication auth) {
        long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
        Cliente cliente = clienteServ.getCliente(idUsuario);
        model.addAttribute("cliente", cliente);
        return "clientePerfil";
    }

    @PostMapping("/atualizarPerfil")
    public String atualizarPerfil(Cliente cliente, Authentication auth) {
        long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
        Cliente clienteAtual = clienteServ.getCliente(idUsuario);
        clienteAtual.setNome(cliente.getNome());
        clienteAtual.setIdade(cliente.getIdade());
        clienteAtual.setAltura(cliente.getAltura());
        clienteAtual.setPeso(cliente.getPeso());
        clienteAtual.setGenero(cliente.getGenero());
        if (cliente.getSenha() != null && !cliente.getSenha().isEmpty()) {
            clienteAtual.setSenha(passwordEncoder.encode(cliente.getSenha()));
        }
        clienteServ.salvarCliente(clienteAtual);
        return "redirect:/cliente/perfil";
    }

}
