package com.banco.cuentaservice.infrastructure.controller;

import com.banco.cuentaservice.application.CuentaService;
import com.banco.cuentaservice.domain.Cuenta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.crearCuenta(cuenta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.obtenerCuenta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizar(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, cuenta));
    }
}
