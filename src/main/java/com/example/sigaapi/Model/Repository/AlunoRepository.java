package com.example.sigaapi.Model.Repository;

import com.example.sigaapi.Model.Entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
