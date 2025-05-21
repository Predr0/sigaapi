package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Modalidade;
import com.example.sigaapi.api.dto.ModalidadeDTO;
import org.modelmapper.ModelMapper;

public class ModalidadeController {
    public Modalidade converter(ModalidadeDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Modalidade.class);
    }
}
