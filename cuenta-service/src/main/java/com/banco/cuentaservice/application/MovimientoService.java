package com.banco.cuentaservice.application;

import com.banco.cuentaservice.domain.Cuenta;
import com.banco.cuentaservice.domain.Movimiento;
import com.banco.cuentaservice.infrastructure.dto.MovimientoDTO;
import com.banco.cuentaservice.infrastructure.exception.SaldoInsuficienteException;
import com.banco.cuentaservice.infrastructure.repository.CuentaRepository;
import com.banco.cuentaservice.infrastructure.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    @Transactional
    public Movimiento registrarMovimiento(MovimientoDTO dto) {
        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (!cuenta.getEstado()) {
            throw new RuntimeException("Cuenta inactiva");
        }

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(dto.getValor());

        if (dto.getValor().compareTo(BigDecimal.ZERO) < 0 && nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException();
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuentaId(dto.getCuentaId());

        return movimientoRepository.save(movimiento);
    }
}
