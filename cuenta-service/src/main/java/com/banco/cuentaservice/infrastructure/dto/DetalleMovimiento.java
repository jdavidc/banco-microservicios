package com.banco.cuentaservice.infrastructure.dto;

import com.banco.cuentaservice.domain.Movimiento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DetalleMovimiento {
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;

    public DetalleMovimiento(Movimiento m) {
        this.fecha = m.getFecha();
        this.tipoMovimiento = m.getTipoMovimiento();
        this.valor = m.getValor();
        this.saldo = m.getSaldo();
    }
}
