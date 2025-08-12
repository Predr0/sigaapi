package com.example.sigaapi.api.dto;

import com.example.sigaapi.Model.Entity.Plano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double valor;
    private Integer duracao;
    private Long idAluno;
    private String nomeAluno;

    public static PlanoDTO create(Plano plano) {
        ModelMapper modelMapper = new ModelMapper();
        PlanoDTO dto = modelMapper.map(plano, PlanoDTO.class);

        if (plano.getAluno() != null) {
            dto.nomeAluno = plano.getAluno().getNome();
            dto.idAluno = plano.getAluno().getId();
        } else {
            dto.nomeAluno = null;
            dto.idAluno = null;
        }

        return dto;
    }
}

