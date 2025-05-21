package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Acompanhamento;
import org.modelmapper.ModelMapper;
import com.example.sigaapi.api.dto.AcompanhamentoDTO;

public class AcompanhamentoController {
    public Acompanhamento Converter(AcompanhamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Acompanhamento acompanhamento = modelMapper.map(dto, Acompanhamento.class);
        return acompanhamento;
    }
}
