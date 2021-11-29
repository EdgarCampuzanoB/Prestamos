package com.example.PrestamosMonetarios.repository;

import com.example.PrestamosMonetarios.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface CuentaDao extends CrudRepository<Cuenta, Integer> {
    Boolean existsByNumeroCuenta(Integer numeroCuenta);

    Cuenta findByNumeroCuenta(Integer numeroCuenta);
}
