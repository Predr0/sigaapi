package com.example.sigaapi.api.controller;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.Model.Entity.Pagamento;
import com.example.sigaapi.api.dto.PagamentoDTO;
import com.example.sigaapi.service.AlunoService;
import com.example.sigaapi.service.FuncionarioService;
import com.example.sigaapi.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final AlunoService alunoService;
    private final FuncionarioService funcionarioService;
    private final PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> get() {
        List<Pagamento> pagamentos = pagamentoService.getPagamentos();
        List<PagamentoDTO> dtoList = pagamentos.stream().map(PagamentoDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }













































































































































    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Pagamento> pagamento = pagamentoService.getPagamentoById(id);
        if (!pagamento.isPresent()) {
            return new ResponseEntity<>("Pagamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(PagamentoDTO.create(pagamento.get()));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody PagamentoDTO dto) {
        try {
            Pagamento pagamento = converter(dto);
            pagamento = pagamentoService.salvar(pagamento);
            return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody PagamentoDTO dto) {
        if (!pagamentoService.getPagamentoById(id).isPresent()) {
            return new ResponseEntity<>("Pagamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Pagamento pagamento = converter(dto);
            pagamento.setId(id);
            pagamentoService.salvar(pagamento);
            return ResponseEntity.ok(pagamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Pagamento converter(PagamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);

        if (dto.getIdAluno() != null) {
            Optional<Aluno> aluno = alunoService.getAlunoById(dto.getIdAluno());
            pagamento.setAluno(aluno.orElse(null));
        }

        if (dto.getIdFuncionario() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionario());
            pagamento.setFuncionario(funcionario.orElse(null));
        }

        return pagamento;
    }
}
