package com.example.sigaapi.service;

import com.example.sigaapi.Model.Entity.Plano;
import com.example.sigaapi.Model.Repository.PlanoRepository;
import com.example.sigaapi.exception.RegraNegocioException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlanoService {
    private PlanoRepository repository;

    public PlanoService(PlanoRepository repository) {
        this.repository = repository;
    }

    public List<Plano> getPlanos() {
        return repository.findAll();
    }

    public Optional<Plano> getPlanoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Plano salvar(Plano plano) {
        validar(plano);
        return repository.save(plano);
    }

    @Transactional
    public void excluir(Plano plano) {
        Objects.requireNonNull(plano.getId());
        repository.delete(plano);
    }

    public void validar(Plano plano) {
        if (plano.getNome() == null || plano.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (plano.getDescricao() == null || plano.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Descrição inválida");
        }
        if (plano.getValor() == null || plano.getValor() <= 0) {
            throw new RegraNegocioException("Valor inválido");
        }
        if (plano.getDuracao() == null || plano.getDuracao() <= 0) {
            throw new RegraNegocioException("Duração inválida");
        }
    }
}
