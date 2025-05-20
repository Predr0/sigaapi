package com.example.sigaapi.service;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Entity.Modalidade;
import com.example.sigaapi.Model.Repository.ModalidadeRepository;
import com.example.sigaapi.exception.RegraNegocioException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ModalidadeService {
    private ModalidadeRepository repository;

    public ModalidadeService(ModalidadeRepository repository) {
        this.repository = repository;
    }

    public List<Modalidade> getModalidades() {
        return repository.findAll();
    }

    public Optional<Modalidade> getModalidadeById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Modalidade salvar(Modalidade modalidade) {
        validar(modalidade);
        return repository.save(modalidade);
    }

    @Transactional
    public void excluir(Modalidade modalidade) {
        Objects.requireNonNull(modalidade.getId());
        repository.delete(modalidade);
    }

    public void validar(Modalidade modalidade) {
        if (modalidade.getDescricao() == null || modalidade.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Descrição invalida");
        }
        if (modalidade.getNome() == null || modalidade.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome invalido");
        }
    }
}