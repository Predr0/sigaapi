package com.example.sigaapi.Model.Repository;

import com.example.sigaapi.Model.Entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
