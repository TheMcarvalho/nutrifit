package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.Refeicao;
import com.edu.vianna.nutrifit.models.enums.ETipoRefeicao;
import com.edu.vianna.nutrifit.repository.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {
    @Autowired
    RefeicaoRepository refeicaoRepo;

    public Refeicao salvarRefeicao(Refeicao refeicao){
        return refeicaoRepo.save(refeicao);
    }
    public List<Refeicao> getRefeicaoPorCliente(Cliente cliente){
        return refeicaoRepo.findByCliente(cliente);
    }
    public List<Refeicao> getRefeicaoPorTipoRefeicao(ETipoRefeicao eTipoRefeicao){
        return refeicaoRepo.findByTipoRefeicao(eTipoRefeicao);
    }
    public List<Refeicao> getTodosAsRefeicoes() {
        return refeicaoRepo.findAll();
    }
    public List<Refeicao> getRefeicaoClienteAndTipo(Cliente cliente, ETipoRefeicao eTipoRefeicao){
        return refeicaoRepo.findByClienteAndTipoRefeicao(cliente,eTipoRefeicao);
    }

    public void deletarRefeicao(Long id){
        refeicaoRepo.deleteById(id);
    }
    public double getTotalCaloriasDiarias(Cliente cliente){
        List<Refeicao> refeicoes = refeicaoRepo.findByCliente(cliente);
        double total = 0;
        for(Refeicao refeicao : refeicoes){
            total += refeicao.getCaloria();
        }
        return total;
    }
}
