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
    private String descricao;
    private String data;
    private String local;
    private Long idAluno;
    private String nomeAluno;
    private Long idFuncionario;
    private String nomeFuncionario;

    public static EventoDTO create(Evento evento) {
        ModelMapper modelMapper = new ModelMapper();
        EventoDTO dto = modelMapper.map(evento, EventoDTO.class);

        if (evento.getFuncionario() != null) {
            dto.setIdFuncionario(evento.getFuncionario().getId());
            dto.setNomeFuncionario(evento.getFuncionario().getNome());
        }

        if (evento.getAluno() != null) {
            dto.setIdAluno(evento.getAluno().getId());
            dto.setNomeAluno(evento.getAluno().getNome());
        }

        return dto;
    }
}

