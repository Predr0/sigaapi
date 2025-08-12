package com.example.sigaapi.Model.Repository;

import com.example.sigaapi.Model.Entity.Acompanhamento;
import com.example.sigaapi.Model.Entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcompanhamentoRepository extends JpaRepository<Acompanhamento, Long> {
}
