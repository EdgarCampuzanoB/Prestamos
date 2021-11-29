package com.example.PrestamosMonetarios.repository;

import com.example.PrestamosMonetarios.entity.Prestamo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository @Transactional
public interface PrestamoDao extends CrudRepository<Prestamo, Integer> {

    List<Prestamo> findAllByNumeroCuenta(Integer numeroCuenta);
}
