package com.example.sigaapi.service;


import com.example.sigaapi.Model.Entity.Evento;
import com.example.sigaapi.Model.Entity.Modalidade;
import com.example.sigaapi.Model.Repository.EventoRepository;
import com.example.sigaapi.Model.Repository.ModalidadeRepository;
import com.example.sigaapi.exception.RegraNegocioException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventoService {
    private EventoRepository repository;
    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public List<Evento> getEvento() {
        return repository.findAll();
    }

    public Optional<Evento> getEventoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Evento salvar(Evento evento) {
        validar(evento);
        return repository.save(evento);
    }

    @Transactional
    public void excluir(Evento evento) {
        Objects.requireNonNull(evento.getId());
        repository.delete(evento);
    }

    public void validar(Evento evento){
            if (evento.getDescricao() == null || evento.getDescricao().trim().equals("")) {
                throw new RegraNegocioException("Descrição invalida");
            }
            if (evento.getNome() == null || evento.getNome().trim().equals("")) {
                throw new RegraNegocioException("Nome invalido");
            }
            if (evento.getData() == null || evento.getData().trim().equals("")) {
                throw new RegraNegocioException("Data invalida");
            }
            if (evento.getLocal() == null || evento.getLocal().trim().equals("")) {
                throw new RegraNegocioException("Local invalido");
            }
        }
    }
