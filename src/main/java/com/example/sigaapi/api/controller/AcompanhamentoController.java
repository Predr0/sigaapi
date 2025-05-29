package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Acompanhamento;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.api.dto.AcompanhamentoDTO;
import com.example.sigaapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/acompanhamentos")
@RequiredArgsConstructor
public class AcompanhamentoController {

    private final FuncionarioService funcionarioService;

    public Acompanhamento converter(AcompanhamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Acompanhamento acompanhamento = modelMapper.map(dto, Acompanhamento.class);

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            acompanhamento.setFuncionario(funcionario.orElse(null));
        }

        return acompanhamento;
    }
}
