package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Mensagem;
import com.example.sigaapi.api.dto.MensagemDTO;
import com.example.sigaapi.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final AlunoService alunoService;

    public Mensagem converter(MensagemDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Mensagem mensagem = modelMapper.map(dto, Mensagem.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            mensagem.setAluno(aluno.orElse(null));
        }

        return mensagem;
    }
}
