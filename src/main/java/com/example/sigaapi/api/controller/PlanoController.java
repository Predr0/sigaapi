package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Plano;
import com.example.sigaapi.api.dto.PlanoDTO;
import org.modelmapper.ModelMapper;

public class PlanoController {
    public Plano converter(PlanoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Plano.class);
    }
}
