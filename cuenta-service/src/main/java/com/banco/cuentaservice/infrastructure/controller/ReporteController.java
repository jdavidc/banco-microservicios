package com.banco.cuentaservice.infrastructure.controller;

import com.banco.cuentaservice.application.CuentaService;
import com.banco.cuentaservice.infrastructure.dto.EstadoCuentaReporte;
import com.banco.cuentaservice.infrastructure.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReporteController {

    private final CuentaService cuentaService;
    private final MovimientoRepository movimientoRepository;

    @GetMapping
    public ResponseEntity<EstadoCuentaReporte> generarReporte(
            @RequestParam String clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<com.banco.cuentaservice.domain.Cuenta> cuentas = cuentaService.obtenerCuentasPorCliente(Long.valueOf(clienteId));
        List<Long> cuentaIds = cuentas.stream().map(com.banco.cuentaservice.domain.Cuenta::getId).toList();

        List<com.banco.cuentaservice.domain.Movimiento> movimientos = movimientoRepository.findByCuentaIdInAndFechaBetween(
                cuentaIds,
                fechaInicio.atStartOfDay(),
                fechaFin.atTime(23, 59, 59)
        );

        EstadoCuentaReporte reporte = new EstadoCuentaReporte(cuentas, movimientos);
        return ResponseEntity.ok(reporte);
    }
}
