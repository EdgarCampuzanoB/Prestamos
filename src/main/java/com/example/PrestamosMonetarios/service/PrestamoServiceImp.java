package com.example.PrestamosMonetarios.service;

import com.example.PrestamosMonetarios.entity.Cuenta;
import com.example.PrestamosMonetarios.entity.Prestamo;
import com.example.PrestamosMonetarios.repository.PrestamoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImp implements PrestamoService {

    @Autowired
    private PrestamoDao prestamoDao;

    //@Autowired
    //private CuentaDao cuentaDao;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

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
        List<Cuenta> cuentas = getCuentas();
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Double getBalanceCuenta(Integer numeroCuenta) {
        List<Cuenta> cuentas = getCuentas();
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equals(numeroCuenta)) {
                return c.getBalance();
            }
        }
        return null;
    }

    public List<Cuenta> obtenerCuentas() {
        return (List<Cuenta>) getCuentas();
    }

    //public void nuevaCuenta(Cuenta cuenta){
    //  cuentaDao.save(cuenta);
    //}

    public List<Cuenta> getCuentas() {
        ResponseEntity<Cuenta[]> cuentaResponseEntity = restTemplate.getForEntity("http://localhost:8081/cuenta/listacuentas", Cuenta[].class);
        Cuenta[] cuenta = cuentaResponseEntity.getBody();
        List<Cuenta> cuentas = Arrays.asList(cuenta);
        return cuentas;
    }


}
