package com.example.sigaapi.api.dto;

import com.example.sigaapi.Model.Entity.Acompanhamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcompanhamentoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Long idFuncionario;
    private String nomeFuncionario;

    public static AcompanhamentoDTO create(Acompanhamento acompanhamento) {
        ModelMapper modelMapper = new ModelMapper();
        AcompanhamentoDTO dto = modelMapper.map(acompanhamento, AcompanhamentoDTO.class);

        if (acompanhamento.getFuncionario() != null) {
            dto.nomeFuncionario = acompanhamento.getFuncionario().getNome();
        } else {
            dto.nomeFuncionario = null;
        }

        return dto;
    }
}

