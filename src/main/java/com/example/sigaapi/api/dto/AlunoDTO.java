package com.example.sigaapi.api.dto;

import com.example.sigaapi.Model.Entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {
    private Long id;
    private String nome;
    private String dataNasc;
    private String cpf;
    private String telefone;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String mensalidade;

    public static AlunoDTO create(Aluno usuario) {
        ModelMapper modelMapper = new ModelMapper();
        AlunoDTO dto = modelMapper.map(usuario, AlunoDTO.class);
        return dto;
    }


}
