package com.edu.vianna.nutrifit.models;

import com.edu.vianna.nutrifit.models.enums.EMeta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private EMeta meta;

    @Column(nullable = false)
    private double pesoAlvo;

    @Column(nullable = false)
    private boolean concluido;

    private LocalDate tempoConclusao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
