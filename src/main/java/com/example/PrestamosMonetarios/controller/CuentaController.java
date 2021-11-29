package com.example.PrestamosMonetarios.controller;

import com.example.PrestamosMonetarios.entity.Cuenta;
import com.example.PrestamosMonetarios.repository.PrestamoDao;
import com.example.PrestamosMonetarios.service.PrestamoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    public PrestamoServiceImp prestamoServiceImp;

    @GetMapping("/lista-cuentas")
    public List<Cuenta> cuentaList(){
        return  prestamoServiceImp.obtenerCuentas();
    }

    @PostMapping("/nueva-cuenta")
    public ResponseEntity<?> nuevaCuenta(@RequestBody Cuenta cuenta){
        prestamoServiceImp.nuevaCuenta(cuenta);
        return new ResponseEntity<>("Se ha creado la cuenta nueva", HttpStatus.CREATED);
    }
}
