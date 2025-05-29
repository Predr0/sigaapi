package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.Model.Entity.Modalidade;
import com.example.sigaapi.api.dto.ModalidadeDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/modalidades")
@RequiredArgsConstructor
public class ModalidadeController {

    private final AlunoService alunoService;
    private final FuncionarioService funcionarioService;

    public Modalidade converter(ModalidadeDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Modalidade modalidade = modelMapper.map(dto, Modalidade.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            modalidade.setAluno(aluno.orElse(null));
        }

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            modalidade.setFuncionario(funcionario.orElse(null));
        }

        return modalidade;
    }
}
