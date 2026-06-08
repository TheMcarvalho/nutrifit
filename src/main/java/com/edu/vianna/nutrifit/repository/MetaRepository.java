package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MetaRepository extends JpaRepository<Meta, Long> {
    List<Meta> findByCliente(Cliente cliente);
    List<Meta> findByClienteAndConcluido(Cliente cliente, boolean concluido);
}
