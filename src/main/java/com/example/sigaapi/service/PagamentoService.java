package com.example.sigaapi.service;

import com.example.sigaapi.Model.Entity.Pagamento;
import com.example.sigaapi.Model.Repository.PagamentoRepository;
import com.example.sigaapi.exception.RegraNegocioException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PagamentoService {
    private PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public List<Pagamento> getPagamentos() {
        return repository.findAll();
    }

    public Optional<Pagamento> getPagamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pagamento salvar(Pagamento pagamento) {
        validar(pagamento);
        return repository.save(pagamento);
    }

    @Transactional
    public void excluir(Pagamento pagamento) {
        Objects.requireNonNull(pagamento.getId());
        repository.delete(pagamento);
    }

    public void validar(Pagamento pagamento) {
        if (pagamento.getValor() == null) {
            throw new RegraNegocioException("Valor inválido");
        }
        if (pagamento.getDatePagamento() == null || pagamento.getDatePagamento().trim().equals("")) {
            throw new RegraNegocioException("Data de pagamento inválida");
        }
        if (pagamento.getFormaPagamento() == null || pagamento.getFormaPagamento().trim().equals("")) {
            throw new RegraNegocioException("Forma de pagamento inválida");
        }
    }
}
