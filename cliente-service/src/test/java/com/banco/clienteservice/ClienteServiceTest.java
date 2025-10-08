package com.banco.clienteservice;

import com.banco.clienteservice.application.ClienteService;
import com.banco.clienteservice.domain.Cliente;
import com.banco.clienteservice.infrastructure.exception.ClienteAlreadyExistsException;
import com.banco.clienteservice.infrastructure.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void crearCliente_debeLanzarExcepcion_siIdentificacionYaExiste() {
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123456789");

        when(clienteRepository.existsByIdentificacion("123456789")).thenReturn(true);

        assertThrows(ClienteAlreadyExistsException.class, () -> {
            clienteService.crearCliente(cliente);
        });
    }
}
