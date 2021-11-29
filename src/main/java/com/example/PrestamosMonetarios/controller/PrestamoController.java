package com.example.PrestamosMonetarios.controller;

import com.example.PrestamosMonetarios.entity.Prestamo;
import com.example.PrestamosMonetarios.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    //@Autowired
    //private CuentaDao cuentaDao;

    @PostMapping("/nuevo-prestamo")
    public ResponseEntity<?> nuevoPrestamo(@RequestBody Prestamo prestamo){
        if(prestamoService.existsBynumeroCuenta(prestamo.getNumeroCuenta())){
            if(prestamoService.getBalanceCuenta(prestamo.getNumeroCuenta()) >= 20000.00){
                prestamo.setFechaInicio(Calendar.getInstance());
                prestamoService.nuevoPrestamo(prestamo);
                return ResponseEntity.ok("Prestamo Aprobado");
            }
            return new ResponseEntity<>("No tiene el saldo suficiente", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("No tiene una cuenta asociada", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/prestamos-por-cuenta/{numeroCuenta}")
    public ResponseEntity<?> obtenerPrestamosByCuenta(@PathVariable ("numeroCuenta") Integer numeroCuenta){
        StringBuilder mensaje = new StringBuilder();
        if(prestamoService.existsBynumeroCuenta(numeroCuenta)) {
            List<Prestamo> prestamos = prestamoService.obtenerPrestamosCliente(numeroCuenta);
            for (Prestamo prestamo:prestamos) {
                 mensaje.append("ID Prestamo: ").append(prestamo.getIdPrestamo()).append(" Adeudo: ").append(prestamo.getMontoTotalPrestamo()).append("\n");
            }
            return new ResponseEntity<>(mensaje.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Error, el numero de cuenta no es correcto", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/prestamoPorId/{id}")
    public ResponseEntity<String> obtenerPrestamoPorId(@PathVariable ("id") Integer id){
        Double deuda;
        Integer idPrestamo;
        idPrestamo = prestamoService.prestamoPorId(id).stream().findFirst().orElseThrow().getIdPrestamo();
         deuda = prestamoService.prestamoPorId(id).stream().findFirst().orElseThrow().getMontoTotalPrestamo();
        String mensaje = "Prestamo " + idPrestamo + "\n"
                + "Cantidad que se debe" + deuda;
        return ResponseEntity.ok(mensaje);
    }
}
