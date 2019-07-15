package com.openwebinars.spring;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.openwebinars.spring.modelo.Empleado;
import com.openwebinars.spring.repositorios.EmpleadoRepositorio;
import com.openwebinars.spring.upload.storage.I_StorageService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el almacenamiento
	 * secundario del proyecto
	 * This interface provides access to application arguments as string array. Let's see the example code for more clarity.
	 * @param storageService Almacenamiento secundario del proyecto
	 * @return
	 */
	@Bean
    CommandLineRunner init(I_StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
	
	
	@Bean
	CommandLineRunner initData(EmpleadoRepositorio repositorio) {
		return (args) -> {

//			Empleado empleado = new Empleado("Luis Miguel López", "luismi.lopez@openwebinars.net", "954000000");
//			Empleado empleado2 = new Empleado("José García", "jose.garcia@openwebinars.net", "954000000");
//			
//			repositorio.save(empleado);
//			repositorio.save(empleado2);
//			
//			repositorio.findAll().forEach(System.out::println);
			
//	Salvamos 3 empleados por defecto en la BD
			repositorio.saveAll(
					Arrays.asList(new Empleado(1, "Antonio García", "antonio.garcia@openwebinars.net", "954000000"),
							new Empleado(2, "María López", "maria.lopez@openwebinars.net", "954000000"),
							new Empleado(3, "Ángel Antúnez", "angel.antunez@openwebinars.net", "954000000")));

		};
	}

}
