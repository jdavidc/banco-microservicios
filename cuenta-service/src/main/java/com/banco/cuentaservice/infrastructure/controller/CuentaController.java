package com.banco.cuentaservice.infrastructure.controller;

import com.banco.cuentaservice.application.CuentaService;
import com.banco.cuentaservice.domain.Cuenta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de cuentas bancarias
 */
@Tag(name = "Cuentas", description = "API para la gestión de cuentas bancarias")
@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @Operation(summary = "Crear una nueva cuenta",
               description = "Crea una nueva cuenta bancaria con la información proporcionada")
    @ApiResponse(responseCode = "200", description = "Cuenta creada exitosamente",
                content = @Content(schema = @Schema(implementation = Cuenta.class)))
    @ApiResponse(responseCode = "400", description = "Datos de la cuenta inválidos")
    @PostMapping
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.crearCuenta(cuenta));
    }

    @Operation(summary = "Obtener una cuenta por ID",
               description = "Obtiene la información de una cuenta específica por su ID")
    @ApiResponse(responseCode = "200", description = "Cuenta encontrada",
                content = @Content(schema = @Schema(implementation = Cuenta.class)))
    @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.obtenerCuenta(id));
    }

    @Operation(summary = "Actualizar una cuenta",
               description = "Actualiza la información de una cuenta existente")
    @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente",
                content = @Content(schema = @Schema(implementation = Cuenta.class)))
    @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizar(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, cuenta));
    }
}
