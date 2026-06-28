package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.config.DTO.UserLogadoDTO;
import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.enums.ENivelAtividade;
import com.edu.vianna.nutrifit.service.ClienteService;
import com.edu.vianna.nutrifit.service.TMBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tmb")
public class TMBController {
    @Autowired
    TMBService tmbServ;
    @Autowired
    ClienteService clienteServ;

    @GetMapping("/listar")
    public String listarTMBPorCliente(Model model, Authentication auth) {
        long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
        Cliente cliente = clienteServ.getCliente(idUsuario);
        model.addAttribute("listarPorCliente", tmbServ.getTMBPorCliente(cliente));
        return "tmb";
    }

    @PostMapping("/salvar")
    public String salvarTMB(ENivelAtividade nivelAtividade, Authentication auth) {
        long idUsuario = ((UserLogadoDTO) auth.getPrincipal()).getIdUser();
        Cliente cliente = clienteServ.getCliente(idUsuario);
        tmbServ.salvarTMB(cliente, nivelAtividade);
        return "redirect:/tmb/listar";
    }

}
