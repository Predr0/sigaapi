package com.example.sigaapi.api.dto;

import com.example.sigaapi.Model.Entity.Modalidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModalidadeDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Long idFuncionario;
    private String nomeFuncionario;
    private Long idAluno;
    private String nomeAluno;

    public static ModalidadeDTO create(Modalidade modalidade) {
        ModelMapper modelMapper = new ModelMapper();
        ModalidadeDTO dto = modelMapper.map(modalidade, ModalidadeDTO.class);

        if (modalidade.getFuncionario() != null) {
            dto.setNomeFuncionario(modalidade.getFuncionario().getNome());
        } else {
            dto.setNomeFuncionario(null);
        }

        if (modalidade.getAluno() != null) {
            dto.setNomeAluno(modalidade.getAluno().getNome());
        } else {
            dto.setNomeAluno(null);
        }

        return dto;
    }
}
