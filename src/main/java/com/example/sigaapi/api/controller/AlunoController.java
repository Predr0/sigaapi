package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.api.dto.AlunoDTO;
import com.example.sigaapi.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @GetMapping()
    public ResponseEntity<List<AlunoDTO>> get() {
        List<Aluno> alunos = service.getAlunos();
        List<AlunoDTO> dtoList = alunos.stream()
                .map(AlunoDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Aluno Converter(AlunoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Aluno.class);
    }
}
