package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Pagamento;
import com.example.sigaapi.api.dto.PagamentoDTO;
import org.modelmapper.ModelMapper;

public class PagamentoController {
    public Pagamento converter(PagamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Pagamento.class);
    }
}
