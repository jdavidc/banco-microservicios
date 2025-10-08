package com.banco.cuentaservice.infrastructure.controller;

import com.banco.cuentaservice.application.CuentaService;
import com.banco.cuentaservice.infrastructure.dto.EstadoCuentaReporte;
import com.banco.cuentaservice.infrastructure.repository.MovimientoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * Controlador REST para la generación de reportes
 */
@Tag(name = "Reportes", description = "API para la generación de reportes del sistema")
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReporteController {

    private final CuentaService cuentaService;
    private final MovimientoRepository movimientoRepository;

    @Operation(summary = "Generar reporte de estado de cuenta",
               description = "Genera un reporte de estado de cuenta para un cliente en un rango de fechas específico")
    @ApiResponse(responseCode = "200", description = "Reporte generado exitosamente",
                content = @Content(schema = @Schema(implementation = EstadoCuentaReporte.class)))
    @ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos")
    @GetMapping
    public ResponseEntity<EstadoCuentaReporte> generarReporte(
            @Parameter(description = "ID del cliente para generar el reporte", required = true, example = "1")
            @RequestParam String clienteId,
            
            @Parameter(description = "Fecha de inicio del reporte (formato: YYYY-MM-DD)", required = true, example = "2023-01-01")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            
            @Parameter(description = "Fecha de fin del reporte (formato: YYYY-MM-DD)", required = true, example = "2023-12-31")
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
