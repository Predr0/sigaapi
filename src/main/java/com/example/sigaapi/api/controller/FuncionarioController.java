package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.api.dto.FuncionarioDTO;
import com.example.sigaapi.exception.RegraNegocioException;
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
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
@CrossOrigin
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> get() {
        List<Funcionario> funcionarios = funcionarioService.getFuncionarios();
        List<FuncionarioDTO> dtoList = funcionarios.stream()
                .map(FuncionarioDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody FuncionarioDTO dto) {
        Funcionario funcionario = converter(dto);
        funcionario = funcionarioService.salvar(funcionario);
        return new ResponseEntity(funcionario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FuncionarioDTO dto) {
        if (!funcionarioService.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        Funcionario funcionario = converter(dto);
        funcionario.setId(id);
        funcionarioService.salvar(funcionario);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            funcionarioService.excluir(funcionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Funcionario converter(FuncionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Funcionario.class);
    }
}
