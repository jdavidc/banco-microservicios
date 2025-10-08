package com.banco.cuentaservice.infrastructure.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo no disponible");
    }
}
