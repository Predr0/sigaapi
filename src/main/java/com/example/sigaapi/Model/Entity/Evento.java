package com.example.sigaapi.Model.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String data;
    private String local;
    private String descricao;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Aluno aluno;

}



