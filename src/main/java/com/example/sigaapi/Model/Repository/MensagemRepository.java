package com.example.sigaapi.Model.Repository;

import com.example.sigaapi.Model.Entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}

