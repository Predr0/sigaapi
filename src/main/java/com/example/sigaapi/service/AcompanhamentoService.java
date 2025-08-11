package com.example.sigaapi.service;

import com.example.sigaapi.Model.Entity.Acompanhamento;
import com.example.sigaapi.Model.Repository.AcompanhamentoRepository;
import com.example.sigaapi.exception.RegraNegocioException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AcompanhamentoService {
    private AcompanhamentoRepository repository;

    public AcompanhamentoService(AcompanhamentoRepository repository) {
        this.repository = repository;
    }

    public List<Acompanhamento> getAcompanhamentos() {
        return repository.findAll();
    }


    public Optional<Acompanhamento> getAcompanhamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Acompanhamento salvar(Acompanhamento acompanhamento) {
        validar(acompanhamento);
        return repository.save(acompanhamento);
    }

    @Transactional
    public void excluir(Acompanhamento acompanhamento) {
        Objects.requireNonNull(acompanhamento.getId());
        repository.delete(acompanhamento);
    }

    public void validar(Acompanhamento acompanhamento) {
        if (acompanhamento.getDescricao() == null || acompanhamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Descrição invalida");
        }
        if (acompanhamento.getNome() == null || acompanhamento.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome invalido");
        }
    }
}
