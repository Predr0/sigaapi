package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Mensagem;
import com.example.sigaapi.api.dto.EventoDTO;
import com.example.sigaapi.exception.RegraNegocioException;
import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Evento;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.EventoService;
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
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final AlunoService alunoService;
    private final FuncionarioService funcionarioService;
    private final EventoService eventoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Evento> eventos = eventoService.getEvento();
        return ResponseEntity.ok(eventos.stream().map(EventoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        if (!evento.isPresent()) {
            return new ResponseEntity("Evento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(evento.map(EventoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody EventoDTO dto) {
        try {
            Evento evento = converter(dto);
            evento = eventoService.salvar(evento);
            return new ResponseEntity(evento, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody EventoDTO dto) {
        Optional<Evento> existente = eventoService.getEventoById(id);
        if (!existente.isPresent()) {
            return new ResponseEntity("Evento não encontrado", HttpStatus.NOT_FOUND);
        }

        try {
            Evento evento = converter(dto);
            evento.setId(id);
            eventoService.salvar(evento);
            return ResponseEntity.ok(evento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        if (!evento.isPresent()) {
            return new ResponseEntity("Evento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            eventoService.excluir(evento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Evento converter(EventoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Evento evento = modelMapper.map(dto, Evento.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            evento.setAluno(aluno.orElse(null));
        }

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            evento.setFuncionario(funcionario.orElse(null));
        }

        return evento;
    }
}
