package com.example.block6personcontrollers;

import com.example.block6personcontrollers.Model.Ciudad;
import com.example.block6personcontrollers.Service.CiudadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block6PersonControllersApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block6PersonControllersApplication.class, args);
	}

	@Bean
	public CommandLineRunner cargarCiudades(CiudadService ciudadService) {
		return args -> {
			ciudadService.agregarCiudad(new Ciudad("Ciudad A", 1000000));
			ciudadService.agregarCiudad(new Ciudad("Ciudad B", 1500000));
			// Agregar más ciudades si es necesario
		};
	}
}
