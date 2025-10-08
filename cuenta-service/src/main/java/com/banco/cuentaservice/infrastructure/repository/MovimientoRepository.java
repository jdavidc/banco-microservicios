package com.banco.cuentaservice.infrastructure.repository;

import com.banco.cuentaservice.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaIdInAndFechaBetween(List<Long> cuentaIds, LocalDateTime inicio, LocalDateTime fin);
}
