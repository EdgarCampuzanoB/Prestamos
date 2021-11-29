package com.example.PrestamosMonetarios.service;

import com.example.PrestamosMonetarios.entity.Cuenta;
import com.example.PrestamosMonetarios.entity.Prestamo;
import com.example.PrestamosMonetarios.repository.CuentaDao;
import com.example.PrestamosMonetarios.repository.PrestamoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImp implements PrestamoService{

    @Autowired
    private PrestamoDao prestamoDao;

    @Autowired
    private CuentaDao cuentaDao;

    @Override
    public void nuevoPrestamo(Prestamo prestamo) {
        prestamoDao.save(prestamo);
    }

    @Override
    public Optional<Prestamo> prestamoPorId(Integer id) {
        return prestamoDao.findById(id);
    }

    @Override
    public List<Prestamo> obtenerPrestamosCliente(Integer numeroCuenta) {
        return prestamoDao.findAllByNumeroCuenta(numeroCuenta);
    }

    @Override
    public Boolean existsBynumeroCuenta(Integer numeroCuenta) {
        return cuentaDao.existsByNumeroCuenta(numeroCuenta);
    }

    @Override
    public Double getBalanceCuenta(Integer numeroCuenta) {
        return cuentaDao.findByNumeroCuenta(numeroCuenta).getBalance();

    }

    public List<Cuenta> obtenerCuentas(){
        return (List<Cuenta>) cuentaDao.findAll();
    }

    public void nuevaCuenta(Cuenta cuenta){
        cuentaDao.save(cuenta);
    }

}
