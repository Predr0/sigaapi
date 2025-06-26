package com.example.sigaapi.api.dto;

import com.example.sigaapi.Model.Entity.Mensagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensagemDTO {
    private Long id;
    private String conteudo;
    private String remetente;
    private String destinatario;
    private String data;
    private Long idAluno;
    private String nomeAluno;

    public static MensagemDTO create(Mensagem mensagem) {
        ModelMapper modelMapper = new ModelMapper();
        MensagemDTO dto = modelMapper.map(mensagem, MensagemDTO.class);
        dto.nomeAluno = mensagem.getAluno().getNome();
        return dto;
    }


}
