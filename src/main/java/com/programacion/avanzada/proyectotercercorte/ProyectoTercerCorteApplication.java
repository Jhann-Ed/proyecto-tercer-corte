package com.programacion.avanzada.proyectotercercorte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.programacion.avanzada.proyectotercercorte")
public class ProyectoTercerCorteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTercerCorteApplication.class, args);
	}

}
