package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.FichaTreino;
import com.edu.vianna.nutrifit.models.ItensFicha;
import com.edu.vianna.nutrifit.repository.ItensFichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItensFichaService {
    @Autowired
    ItensFichaRepository itensFichaRepo;

    public List<ItensFicha> getItensPorFicha(FichaTreino fichaTreino){
        return itensFichaRepo.findByFichaTreino(fichaTreino);
    }

    public ItensFicha marcarComoFeito(Long id){
        Optional<ItensFicha> itemF = itensFichaRepo.findById(id);
        if (itemF.isPresent()){
            itemF.get().setFeito(true);
            return itensFichaRepo.save(itemF.get());
        }
        return null;
    }
    public ItensFicha salvarItensFicha(ItensFicha itensFicha){
        return itensFichaRepo.save(itensFicha);
    }
    public void deletarItensFicha(Long id){
         itensFichaRepo.deleteById(id);
    }
}
