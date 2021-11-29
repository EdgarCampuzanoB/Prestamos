package com.example.PrestamosMonetarios.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "prestamos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPrestamo;
    private Integer numeroCuenta;
    private Double montoTotalPrestamo;
    private Integer plazoMeses;
    private Calendar fechaInicio;
    private Float tasaInteres;

}
