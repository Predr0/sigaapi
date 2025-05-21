package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Mensagem;
import com.example.sigaapi.api.dto.MensagemDTO;
import org.modelmapper.ModelMapper;

public class MensagemController {
    public Mensagem converter(MensagemDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Mensagem.class);
    }
}
