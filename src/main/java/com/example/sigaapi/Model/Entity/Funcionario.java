package com.example.sigaapi.Model.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Funcionario extends Pessoa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cargo;
    private double salario;
}



