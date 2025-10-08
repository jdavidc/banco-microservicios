package com.banco.cuentaservice.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimientoDTO {
    private Long cuentaId;
    private String tipoMovimiento;
    private BigDecimal valor;
}
