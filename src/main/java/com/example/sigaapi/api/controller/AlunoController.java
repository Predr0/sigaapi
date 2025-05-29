package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import com.example.sigaapi.api.dto.AlunoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {
    public Aluno Converter(AlunoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Aluno.class);
    }
}
