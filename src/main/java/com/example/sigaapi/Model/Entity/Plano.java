package com.example.sigaapi.Model.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int alunoId;
    private String nome;
    private String descricao;
    private double valor;
    private int duracao;

    @ManyToOne
    private Aluno aluno;
}

