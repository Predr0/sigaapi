package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Acompanhamento;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.api.dto.AcompanhamentoDTO;
import com.example.sigaapi.exception.RegraNegocioException;
import com.example.sigaapi.service.AcompanhamentoService;
import com.example.sigaapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/acompanhamentos")
@RequiredArgsConstructor
public class AcompanhamentoController {

    private final FuncionarioService funcionarioService;
    private final AcompanhamentoService acompanhamentoService;

    @GetMapping()
    public ResponseEntity<List<AcompanhamentoDTO>> get() {
        List<Acompanhamento> acompanhamentos = acompanhamentoService.getAcompanhamentos();
        List<AcompanhamentoDTO> dtoList = acompanhamentos.stream()
                .map(AcompanhamentoDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Acompanhamento> acompanhamento = acompanhamentoService.getAcompanhamentoById(id);
        if (!acompanhamento.isPresent()) {
            return new ResponseEntity("Acompanhamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(AcompanhamentoDTO.create(acompanhamento.get()));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody AcompanhamentoDTO dto) {
        Acompanhamento acompanhamento = converter(dto);
        acompanhamento = acompanhamentoService.salvar(acompanhamento);
        return new ResponseEntity(AcompanhamentoDTO.create(acompanhamento), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AcompanhamentoDTO dto) {
        if (!acompanhamentoService.getAcompanhamentoById(id).isPresent()) {
            return new ResponseEntity("Acompanhamento não encontrado", HttpStatus.NOT_FOUND);
        }
        Acompanhamento acompanhamento = converter(dto);
        acompanhamento.setId(id);
        acompanhamentoService.salvar(acompanhamento);
        return ResponseEntity.ok(AcompanhamentoDTO.create(acompanhamento));
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Acompanhamento> acompanhamento = acompanhamentoService.getAcompanhamentoById(id);
        if (!acompanhamento.isPresent()) {
            return new ResponseEntity("Acompanhamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            acompanhamentoService.excluir(acompanhamento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Acompanhamento converter(AcompanhamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Acompanhamento acompanhamento = modelMapper.map(dto, Acompanhamento.class);

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            acompanhamento.setFuncionario(funcionario.orElse(null));
        }
        return acompanhamento;
    }
}
