package com.example.sigaapi.Model.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private String datePagamento;
    private String formaPagamento;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Funcionario funcionario;
}
