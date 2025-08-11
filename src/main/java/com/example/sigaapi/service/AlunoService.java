package com.example.sigaapi.service;

import com.example.sigaapi.Model.Entity.Aluno;
import com.example.sigaapi.Model.Repository.AlunoRepository;
import com.example.sigaapi.exception.RegraNegocioException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlunoService {
    private AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> getAlunos() {
        return repository.findAll();
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Aluno salvar(Aluno aluno) {
        validar(aluno);
        return repository.save(aluno);
    }

    @Transactional
    public void excluir(Aluno aluno) {
        Objects.requireNonNull(aluno.getId());
        repository.delete(aluno);
    }

    public void validar(Aluno aluno) {
        if (aluno.getMensalidade() == null || aluno.getMensalidade().trim().equals("")) {
            throw new RegraNegocioException("Mensalidade invalida");
        }
        if (aluno.getNome() == null || aluno.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome invalida");
        }
        if (aluno.getTelefone() == null || aluno.getTelefone().trim().equals("")) {
            throw new RegraNegocioException("Telefone invalido");
        }
    }
}
