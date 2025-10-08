package com.banco.clienteservice.application;

import com.banco.clienteservice.domain.Cliente;
import com.banco.clienteservice.infrastructure.dto.ClienteCreadoEvent;
import com.banco.clienteservice.infrastructure.exception.ClienteAlreadyExistsException;
import com.banco.clienteservice.infrastructure.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final RabbitTemplate rabbitTemplate;

    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
            throw new ClienteAlreadyExistsException(cliente.getIdentificacion());
        }
        Cliente guardado = clienteRepository.save(cliente);
        //rabbitTemplate.convertAndSend("cliente.creado", guardado);
        ClienteCreadoEvent event = new ClienteCreadoEvent();
        event.setClienteId(guardado.getClienteId());
        event.setIdentificacion(guardado.getIdentificacion());
        event.setNombre(guardado.getNombre());

        rabbitTemplate.convertAndSend("cliente.creado", event);
        return guardado;
    }

    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente cliente = obtenerCliente(id);
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setGenero(clienteActualizado.getGenero());
        cliente.setEdad(clienteActualizado.getEdad());
        cliente.setDireccion(clienteActualizado.getDireccion());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setContrasena(clienteActualizado.getContrasena());
        cliente.setEstado(clienteActualizado.getEstado());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
