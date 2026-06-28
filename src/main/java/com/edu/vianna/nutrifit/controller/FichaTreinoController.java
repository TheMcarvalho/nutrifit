package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.config.DTO.UserLogadoDTO;
import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.FichaTreino;
import com.edu.vianna.nutrifit.service.ClienteService;
import com.edu.vianna.nutrifit.service.ExercicioService;
import com.edu.vianna.nutrifit.service.FichaTreinoService;
import com.edu.vianna.nutrifit.service.ItensFichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import java.util.Optional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fichaTreino")
public class FichaTreinoController {
    @Autowired
    FichaTreinoService fichaTreinoServ;
    @Autowired
    ClienteService clienteServ;
    @Autowired
    ExercicioService exercicioServ;
    @Autowired
    ItensFichaService itensFichaServ;

    @GetMapping("/listar")
    public String listar(Model model, Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            model.addAttribute("listarFichas", fichaTreinoServ.getTodosAsFichas());
        } else {
            long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
            Cliente cliente = clienteServ.getCliente(idUsuario);
            model.addAttribute("listarFichas", fichaTreinoServ.getTodasAsFichasCliente(cliente));
        }
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
    public String salvar(FichaTreino fichaTreino, Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CLIENTE"))) {
            long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
            Cliente cliente = clienteServ.getCliente(idUsuario);
            fichaTreino.setCliente(cliente);
        }
        fichaTreinoServ.salvarFichaTreino(fichaTreino);
        return "redirect:/fichaTreino/listar";
    }

    @PostMapping("/deletar")
    public String deletar(FichaTreino fichaTreino) {
        fichaTreinoServ.deletarFichaTreino(fichaTreino.getId());
        return "redirect:/fichaTreino/listar";
    }

    @PostMapping("/marcarFeito")
    public String marcarFeito(Long id, Long fichaId) {
        itensFichaServ.marcarComoFeito(id);
        return "redirect:/fichaTreino/ver?id=" + fichaId;
    }

}
