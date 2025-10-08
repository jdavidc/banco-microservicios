package com.banco.cuentaservice.infrastructure.dto;

import lombok.Data;

@Data
public class ClienteCreadoEvent {
    private Long clienteId;
    private String identificacion;
    private String nombre;
    // Solo los campos que necesitas
}