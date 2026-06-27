package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.Meta;
import com.edu.vianna.nutrifit.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {
    @Autowired
    MetaRepository metaRepo;

    public List<Meta> getTodosAsMetas() {
        return metaRepo.findAll();
    }
    public Meta salvarMeta(Meta meta){
        return  metaRepo.save(meta);
    }
    public void deletarMeta(Long id){
        metaRepo.deleteById(id);
    }
    public List<Meta> getMetasCliente(Cliente cliente){
        return metaRepo.findByCliente(cliente);
    }
    public List<Meta> getMetasPorStatus(Cliente cliente, boolean concluido){
        return metaRepo.findByClienteAndConcluido(cliente, concluido);
    }
    public Meta marcarComoConcluida(Long id){
        Optional<Meta> meta = metaRepo.findById(id);
        if(meta.isPresent()){
            meta.get().setConcluido(true);
            return metaRepo.save(meta.get());
        }
        return null;
    }
}
