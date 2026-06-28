package com.edu.vianna.nutrifit.controller;

import com.edu.vianna.nutrifit.models.enums.ENivelAtividade;
import com.edu.vianna.nutrifit.service.TMBService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/listar")
    public String listarTMBPorCliente(Model model){
        model.addAttribute("listarPorCliente", tmbServ.getTodosTMB());
        return "tmb";
    }

    @PostMapping("/salvar")
    public String salvarTMB(ENivelAtividade nivelAtividade) {
        tmbServ.salvarTMB(null, nivelAtividade);
        return "redirect:/tmb/listar";
    }

}
