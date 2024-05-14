package com.programacion.avanzada.proyectotercercorte;

import com.programacion.avanzada.proyectotercercorte.Repositories.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    void testEmpleadoRepositoryIsNotNull() {
        assertThat(empleadoRepository).isNotNull();
    }
}