package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.config.DTO.UserLogadoDTO;
import com.edu.vianna.nutrifit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.edu.vianna.nutrifit.models.Cliente;

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
    public String dashboard(Model model, Authentication auth) {
        if (auth != null) {
            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                model.addAttribute("totalClientes", clienteServ.getTodosClientes().size());
                model.addAttribute("totalExercicios", exercicioServ.getTodosExercicios().size());
                model.addAttribute("totalFichas", fichaTreinoServ.getTodosAsFichas().size());
            } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CLIENTE"))) {
                long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
                Cliente cliente = clienteServ.getCliente(idUsuario);
                model.addAttribute("totalFichas", fichaTreinoServ.getTodasAsFichasCliente(cliente).size());
                model.addAttribute("todasMetas", metaServ.getMetasCliente(cliente));
                model.addAttribute("metasPendentes", metaServ.getMetasPorStatus(cliente, false));
                model.addAttribute("totalCalorias", refeicaoServ.getTotalCaloriasDiarias(cliente));
                model.addAttribute("tmbRecente", tmbServ.getTMBMaisRecente(cliente));
            }
        }
        return "dashboard";
    }
}
