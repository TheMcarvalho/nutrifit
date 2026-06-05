package com.edu.vianna.nutrifit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItensFicha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio;

    @Column(nullable = false)
    private int repeticoes;

    @Column(nullable = false)
    private int series;

    @Column(nullable = false)
    private double carga;

    @Column(nullable = false)
    private boolean feito;

    @ManyToOne
    @JoinColumn(name = "ficha_treino_id")
    private FichaTreino fichaTreino;
}
