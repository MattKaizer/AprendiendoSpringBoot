package com.openwebinars.spring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.openwebinars.spring.modelo.Empleado;

public interface EmpleadoRepositorio extends CrudRepository<Empleado, Long> {
	
	

}
