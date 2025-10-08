package com.banco.clienteservice.infrastructure.dto;

import lombok.Data;

@Data
public class ClienteCreadoEvent {
    private Long clienteId;
    private String identificacion;
    private String nombre;
}