package com.programacion.avanzada.proyectotercercorte.Repositories;

import com.programacion.avanzada.proyectotercercorte.Entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByPagoRealizadoFalse();

    List<Empleado> findByRol(String rol);
}