package com.programacion.avanzada.proyectotercercorte.Services;

import com.programacion.avanzada.proyectotercercorte.Entities.Empleado;
import com.programacion.avanzada.proyectotercercorte.Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> getEmpleadoById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado createEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado updateEmpleado(Long id, Empleado empleadoDetails) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setNombre(empleadoDetails.getNombre());
        empleado.setApellido(empleadoDetails.getApellido());
        empleado.setEmail(empleadoDetails.getEmail());
        empleado.setTelefono(empleadoDetails.getTelefono());
        empleado.setSalario(empleadoDetails.getSalario());
        empleado.setRol(empleadoDetails.getRol());

        return empleadoRepository.save(empleado);
    }

    public void deleteEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    public List<Empleado> getEmpleadosByRol(String rol) {
        return empleadoRepository.findByRol(rol);
    }

    public List<Empleado> getEmpleadosNoPagados() {
        return empleadoRepository.findByPagoRealizadoFalse();
    }

    public void marcarPagoRealizado(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        empleado.setPagoRealizado(true);
        empleadoRepository.save(empleado);
    }
}