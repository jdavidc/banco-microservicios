package com.banco.cuentaservice.infrastructure.controller;

import com.banco.cuentaservice.application.MovimientoService;
import com.banco.cuentaservice.domain.Movimiento;
import com.banco.cuentaservice.infrastructure.dto.MovimientoDTO;
import com.banco.cuentaservice.infrastructure.exception.SaldoInsuficienteException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de movimientos de cuentas
 */
@Tag(name = "Movimientos", description = "API para la gestión de movimientos de cuentas bancarias")
@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Operation(summary = "Registrar un movimiento",
               description = "Registra un nuevo movimiento en una cuenta (depósito o retiro)")
    @ApiResponse(responseCode = "200", description = "Movimiento registrado exitosamente",
                content = @Content(schema = @Schema(implementation = Movimiento.class)))
    @ApiResponse(responseCode = "400", description = "Datos del movimiento inválidos")
    @ApiResponse(responseCode = "400", description = "Saldo insuficiente",
                content = @Content(schema = @Schema(implementation = SaldoInsuficienteException.class)))
    @PostMapping
    public ResponseEntity<Movimiento> registrar(@RequestBody MovimientoDTO dto) {
        return ResponseEntity.ok(movimientoService.registrarMovimiento(dto));
    }
}
