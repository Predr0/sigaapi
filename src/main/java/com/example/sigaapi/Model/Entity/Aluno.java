package com.example.sigaapi.Model.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Aluno extends Pessoa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensalidade;
}