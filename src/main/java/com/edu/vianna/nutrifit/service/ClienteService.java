package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.enums.EGenero;
import com.edu.vianna.nutrifit.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository cliRepo;

    public List<Cliente> getClientePorNome(String nome){
        return cliRepo.findByNome(nome);
    }
    
    public Optional<Cliente> getClientePorId(Long id){
        return cliRepo.findById(id);
    }

    public List<Cliente> getTodosClientes(){
        return cliRepo.findAll();
    }

    public List<Cliente> getClientePorGenero(EGenero genero){
        return cliRepo.findByGenero(genero);
    }

    public List<Cliente>getClientePorFaixaDeIdade(int min,int max){
        return cliRepo.findByIdadeBetween(min,max);
    }

    public Cliente salvarCliente(Cliente cliente){
        return cliRepo.save(cliente);
    }
    public void deletarCliente(Long id){
        cliRepo.deleteById(id);
    }
}
