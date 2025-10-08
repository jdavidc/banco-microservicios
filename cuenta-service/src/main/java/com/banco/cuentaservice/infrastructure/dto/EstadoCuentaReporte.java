package com.banco.cuentaservice.infrastructure.dto;

import com.banco.cuentaservice.domain.Cuenta;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class EstadoCuentaReporte {
    private List<Cuenta> cuentas;
    private List<DetalleMovimiento> movimientos;

    public EstadoCuentaReporte(List<Cuenta> cuentas, List<com.banco.cuentaservice.domain.Movimiento> movimientos) {
        this.cuentas = cuentas;
        this.movimientos = movimientos.stream()
                .map(DetalleMovimiento::new)
                .collect(Collectors.toList());
    }
}
