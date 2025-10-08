package com.banco.clienteservice.infrastructure.controller;

import com.banco.clienteservice.application.ClienteService;
import com.banco.clienteservice.domain.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de clientes
 */
@Tag(name = "Clientes", description = "API para la gestión de clientes")
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Crear un nuevo cliente",
               description = "Crea un nuevo cliente con la información proporcionada")
    @ApiResponse(responseCode = "200", description = "Cliente creado exitosamente",
                content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Datos del cliente inválidos")
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    @Operation(summary = "Obtener un cliente por ID",
               description = "Obtiene la información de un cliente específico por su ID")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerCliente(id));
    }

    @Operation(summary = "Actualizar un cliente",
               description = "Actualiza la información de un cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente",
                content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    @Operation(summary = "Eliminar un cliente",
               description = "Elimina un cliente por su ID")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
