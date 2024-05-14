package com.programacion.avanzada.proyectotercercorte.Controller;

import com.programacion.avanzada.proyectotercercorte.Entities.Empleado;
import com.programacion.avanzada.proyectotercercorte.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.getEmpleadoById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.createEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetails) {
        try {
            Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleadoDetails);
            return ResponseEntity.ok(updatedEmpleado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roles/{rol}")
    public List<Empleado> getEmpleadosByRol(@PathVariable String rol) {
        return empleadoService.getEmpleadosByRol(rol);
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @PostMapping("/gestion")
    public Empleado createEmpleadoGestion(@RequestBody Empleado empleado) {
        return empleadoService.createEmpleado(empleado);
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @PutMapping("/gestion/{id}")
    public ResponseEntity<Empleado> updateEmpleadoGestion(@PathVariable Long id, @RequestBody Empleado empleadoDetails) {
        try {
            Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleadoDetails);
            return ResponseEntity.ok(updatedEmpleado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @DeleteMapping("/gestion/{id}")
    public ResponseEntity<Void> deleteEmpleadoGestion(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}