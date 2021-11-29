package com.example.PrestamosMonetarios.service;

import com.example.PrestamosMonetarios.entity.Prestamo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PrestamoService {
    void nuevoPrestamo(Prestamo prestamo);

    Optional<Prestamo> prestamoPorId(Integer id);

    List<Prestamo> obtenerPrestamosCliente(Integer numeroCuenta);

    Boolean existsBynumeroCuenta(Integer numeroCuenta);
    Double getBalanceCuenta(Integer numeroCuenta);
}
