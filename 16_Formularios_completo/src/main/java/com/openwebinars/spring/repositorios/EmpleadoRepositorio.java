package com.openwebinars.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openwebinars.spring.modelo.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
	
	List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContains(
			String nombre, String email, String telefono);
	

}
