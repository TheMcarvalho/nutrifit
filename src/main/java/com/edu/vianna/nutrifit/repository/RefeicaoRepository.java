package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Cliente;
import com.edu.vianna.nutrifit.models.Refeicao;
import com.edu.vianna.nutrifit.models.enums.ETipoRefeicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao,Long> {
    List<Refeicao> findByCliente(Cliente cliente);
    List<Refeicao> findByTipoRefeicao(ETipoRefeicao tipoRefeicao);
    List<Refeicao> findByClienteAndTipoRefeicao(Cliente cliente, ETipoRefeicao tipoRefeicao);
}
