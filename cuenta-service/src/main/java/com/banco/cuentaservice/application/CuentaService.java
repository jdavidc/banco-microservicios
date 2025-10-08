package com.banco.cuentaservice.application;

import com.banco.cuentaservice.domain.Cuenta;
import com.banco.cuentaservice.infrastructure.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtenerCuenta(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        Cuenta cuenta = obtenerCuenta(id);
        cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuenta.setEstado(cuentaActualizada.getEstado());
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> obtenerCuentasPorCliente(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}
