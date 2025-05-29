package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Plano;
import com.example.sigaapi.api.dto.PlanoDTO;
import com.example.sigaapi.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final AlunoService alunoService;

    public Plano converter(PlanoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Plano plano = modelMapper.map(dto, Plano.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            plano.setAluno(aluno.orElse(null));
        }

        return plano;
    }
}
