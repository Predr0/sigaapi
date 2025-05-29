package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Evento;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.api.dto.EventoDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final AlunoService alunoService;
    private final FuncionarioService funcionarioService;

    public Evento converter(EventoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Evento evento = modelMapper.map(dto, Evento.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            evento.setAluno(aluno.orElse(null));
        }

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            evento.setFuncionario(funcionario.orElse(null));
        }

        return evento;
    }
}
