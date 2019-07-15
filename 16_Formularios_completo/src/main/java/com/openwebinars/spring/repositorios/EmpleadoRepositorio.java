package com.openwebinars.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openwebinars.spring.modelo.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
	
	

}
