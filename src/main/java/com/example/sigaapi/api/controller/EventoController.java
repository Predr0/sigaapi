package com.example.sigaapi.api.dto.controller;

import com.example.sigaapi.Model.Entity.Evento;
import org.modelmapper.ModelMapper;
import com.example.sigaapi.api.dto.EventoDTO;

public class EventoController {
    public Evento Converter(EventoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Evento evento = modelMapper.map(dto, Evento.class);
        return evento;
    }
}
