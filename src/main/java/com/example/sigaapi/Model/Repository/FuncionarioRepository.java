package com.example.sigaapi.Model.Repository;

import com.example.sigaapi.Model.Entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
