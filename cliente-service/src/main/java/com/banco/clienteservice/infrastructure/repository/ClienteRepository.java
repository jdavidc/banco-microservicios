package com.banco.clienteservice.infrastructure.repository;

import com.banco.clienteservice.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByIdentificacion(String identificacion);
}
