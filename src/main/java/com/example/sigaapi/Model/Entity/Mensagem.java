package com.example.sigaapi.Model.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int alunoId;
    private String remetente;
    private String destinatario;
    private String data;
    private String hora;
    private String conteudo;

    @ManyToOne
    private Aluno aluno;

}
