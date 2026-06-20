package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.FichaTreino;
import com.edu.vianna.nutrifit.repository.FichaTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaTreinoService {
    @Autowired
    FichaTreinoRepository fichaTreinoRepo;
    public FichaTreino getFichaPorNome(String nome){
        return fichaTreinoRepo.findByNome(nome);
    }

    public Optional<FichaTreino> getFichaPorId(Long id){
        return fichaTreinoRepo.findById(id);
    }

    public List<FichaTreino> getTodasAsFichasCliente(Cliente cliente){
        return fichaTreinoRepo.findByCliente(cliente);
    }

    public FichaTreino salvarFichaTreino(FichaTreino fichaTreino){
        return  fichaTreinoRepo.save(fichaTreino);
    }

    public void deletarFichaTreino(Long id){
        fichaTreinoRepo.deleteById(id);
    }

    public List<FichaTreino> getTodosAsFichas() {
        return fichaTreinoRepo.findAll();
    }
}
