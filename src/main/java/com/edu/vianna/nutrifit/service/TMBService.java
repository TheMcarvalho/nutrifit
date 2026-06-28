package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.TMB;
import com.edu.vianna.nutrifit.models.enums.EGenero;
import com.edu.vianna.nutrifit.models.enums.ENivelAtividade;
import com.edu.vianna.nutrifit.repository.TMBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TMBService {
    @Autowired
    TMBRepository tmbRepo;

    private double calcularTMB(Cliente cliente){
        double resultadoTMB;
        if (cliente.getGenero() == EGenero.MASCULINO){
            resultadoTMB =  88.36 + (13.4 * cliente.getPeso()) + (4.8 * cliente.getAltura()) - (5.7 * cliente.getIdade());
        }else{
            resultadoTMB =447.6 + (9.2 * cliente.getPeso())  + (3.1 * cliente.getAltura()) - (4.3 * cliente.getIdade());
        }
        return  resultadoTMB;
    }
    public List<TMB> getTodosTMB() {
        return tmbRepo.findAll();
    }
    public List<TMB> getTMBPorCliente(Cliente cliente){
        return tmbRepo.findByCliente(cliente);
    }

    public TMB getTMBMaisRecente(Cliente cliente){
        return tmbRepo.findTopByClienteOrderByDataDescIdDesc(cliente);
    }

    private double necessidadeCalorica(double tmb,ENivelAtividade eNivelAtividade){
        switch (eNivelAtividade){
            case SEDENTARIO -> {
                return tmb * 1.2;
            }
            case LEVEMENTE_ATIVO -> {
                return tmb * 1.375;
            }
            case MODERADAMENTE_ATIVO -> {
                return tmb * 1.55;
            }
            case MUITO_ATIVO -> {
                return tmb * 1.725;
            }
            case EXTREMAMENTE_ATIVO -> {
                return tmb * 1.9;
            }
            default -> {
                return 0;
            }
        }
    }
    public TMB salvarTMB(Cliente cliente, ENivelAtividade eNivelAtividade){
        double resultadoTMB = calcularTMB(cliente);
        double necessidadeC = necessidadeCalorica(resultadoTMB,eNivelAtividade);

        TMB tmb = new TMB();
        tmb.setCliente(cliente);
        tmb.setResultadoTMB(resultadoTMB);
        tmb.setNecessidadeCalorica(necessidadeC);
        tmb.setNivelAtividade(eNivelAtividade);
        tmb.setData(LocalDate.now());

        return tmbRepo.save(tmb);
    }
}
