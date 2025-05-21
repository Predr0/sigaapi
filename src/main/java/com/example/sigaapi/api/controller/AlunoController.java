package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import org.modelmapper.ModelMapper;
import com.example.sigaapi.api.dto.AlunoDTO;

public class AlunoController {
    public Aluno Converter(AlunoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Aluno aluno = modelMapper.map(dto, Aluno.class);
        return aluno;
    }
}
