package com.edu.vianna.nutrifit.models;

import com.edu.vianna.nutrifit.models.enums.ENivelAtividade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TMB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ENivelAtividade nivelAtividade;

    @Column(nullable = false)
    private double necessidadeCalorica;

    @Column(nullable = false)
    private double resultadoTMB;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
