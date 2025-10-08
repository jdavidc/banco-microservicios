package com.banco.cuentaservice.infrastructure.controller;

import com.banco.cuentaservice.application.MovimientoService;
import com.banco.cuentaservice.domain.Movimiento;
import com.banco.cuentaservice.infrastructure.dto.MovimientoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrar(@RequestBody MovimientoDTO dto) {
        return ResponseEntity.ok(movimientoService.registrarMovimiento(dto));
    }
}
