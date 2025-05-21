package com.example.sigaapi.api.dto;

import com.example.sigaapi.Model.Entity.Evento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {
    private Long id;
    private String nome;
    private Long idAluno;
    private String nomeAluno;
    private Long idFuncionario;
    private String nomeFuncionario;

    public static EventoDTO create(Evento evento) {
        ModelMapper modelMapper = new ModelMapper();
        EventoDTO dto = modelMapper.map(evento, EventoDTO.class);
        dto.nomeFuncionario = evento.getFuncionario().getNome();
        dto.nomeAluno = evento.getAluno().getNome();
        return dto;
    }

}
