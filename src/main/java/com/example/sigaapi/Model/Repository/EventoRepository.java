package com.example.sigaapi.Model.Repository;

import com.example.sigaapi.Model.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
