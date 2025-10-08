package com.banco.clienteservice.infrastructure.exception;

public class ClienteAlreadyExistsException extends RuntimeException {
    public ClienteAlreadyExistsException(String identificacion) {
        super("Cliente con identificaciÃ³n " + identificacion + " ya existe.");
    }
}
