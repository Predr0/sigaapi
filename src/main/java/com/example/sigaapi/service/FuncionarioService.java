package com.example.sigaapi.service;

import com.example.sigaapi.Model.Entity.Acompanhamento;
import com.example.sigaapi.Model.Entity.Funcionario;
import com.example.sigaapi.Model.Repository.FuncionarioRepository;
import com.example.sigaapi.api.dto.AcompanhamentoDTO;
import com.example.sigaapi.exception.RegraNegocioException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> getFuncionarios() {
        return repository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        validar(funcionario);
        return repository.save(funcionario);
    }
    @Transactional
    public void excluir(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getId());
        repository.delete(funcionario);
    }

    public void validar(Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (funcionario.getCargo() == null || funcionario.getCargo().trim().equals("")) {
            throw new RegraNegocioException("Cargo inválido");
        }
        if (funcionario.getSalario() == null) {
            throw new RegraNegocioException("Salário inválido");
        }
    }
}
