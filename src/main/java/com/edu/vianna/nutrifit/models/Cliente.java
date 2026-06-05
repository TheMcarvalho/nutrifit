package com.edu.vianna.nutrifit.models;

import com.edu.vianna.nutrifit.models.enums.EGenero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente extends Usuario{
    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private double altura;

    @Column(nullable = false)
    private double peso;

    @Enumerated(EnumType.STRING)
    private EGenero genero;

    @OneToMany(mappedBy = "cliente")
    private List<FichaTreino> fichaTreinos;
}
