package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Funcionario;
import org.modelmapper.ModelMapper;
import com.example.sigaapi.api.dto.FuncionarioDTO;

public class FuncionarioController {
    public Funcionario Converter(FuncionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        return funcionario;
    }
}
