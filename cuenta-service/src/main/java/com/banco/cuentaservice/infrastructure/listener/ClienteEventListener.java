package com.banco.cuentaservice.infrastructure.listener;

import com.banco.cuentaservice.application.CuentaService;
import com.banco.cuentaservice.domain.Cuenta;
import com.banco.cuentaservice.infrastructure.dto.ClienteCreadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClienteEventListener {

    private final CuentaService cuentaService;

    @RabbitListener(queues = "cliente.creado")
    public void handleClienteCreado(ClienteCreadoEvent cliente) {
        log.info("Cliente recibido para creación de cuenta: ID={}, Nombre={}",
                cliente.getClienteId(), cliente.getNombre());

        // Crear cuenta por defecto
        Cuenta cuentaPorDefecto = new Cuenta();
        cuentaPorDefecto.setNumeroCuenta(generarNumeroCuenta());
        cuentaPorDefecto.setTipoCuenta("Ahorros");
        cuentaPorDefecto.setSaldoInicial(BigDecimal.valueOf(1000));
        cuentaPorDefecto.setEstado(true);
        cuentaPorDefecto.setClienteId(cliente.getClienteId());

        cuentaService.crearCuenta(cuentaPorDefecto);

        log.info("Cuenta por defecto creada para cliente ID: {}", cliente.getClienteId());
    }

    private String generarNumeroCuenta() {
        // Generador simple: puedes mejorar con UUID, secuencia, etc.
        return "CTA" + System.currentTimeMillis();
    }
}