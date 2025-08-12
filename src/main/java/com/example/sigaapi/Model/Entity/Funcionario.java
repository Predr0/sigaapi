package com.example.sigaapi.Model.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Funcionario extends Pessoa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cargo;
    private Double salario;
}



