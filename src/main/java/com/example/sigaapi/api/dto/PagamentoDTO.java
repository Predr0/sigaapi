package com.example.sigaapi.api.dto;

import com.example.sigaapi.Model.Entity.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {
    private Long id;
    private Double valor;
    private Long idFuncionario;
    private String nomeFuncionario;
    private Long idAluno;
    private String nomeAluno;
    public static PagamentoDTO create(Pagamento pagamento) {
        ModelMapper modelMapper = new ModelMapper();
        PagamentoDTO dto = modelMapper.map(pagamento, PagamentoDTO.class);
        dto.nomeFuncionario = pagamento.getFuncionario().getNome();
        dto.nomeAluno = pagamento.getAluno().getNome();
        return dto;
    }

}
