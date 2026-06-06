package com.edu.vianna.nutrifit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ETipoRefeicao tipoRefeicao;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double caloria;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
