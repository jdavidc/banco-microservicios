package com.banco.cuentaservice;

import com.banco.cuentaservice.domain.Cuenta;
import com.banco.cuentaservice.infrastructure.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class CuentaServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test_cuenta")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        // Añadir estas propiedades para que Hibernate cree las tablas
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.jpa.show-sql", () -> "true");
    }

    @Autowired
    private CuentaRepository cuentaRepository;

    @Test
    void guardarCuenta_debePersistirEnBD() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(BigDecimal.valueOf(1000));
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        Cuenta guardada = cuentaRepository.save(cuenta);
        assertThat(guardada.getId()).isNotNull();
    }
}