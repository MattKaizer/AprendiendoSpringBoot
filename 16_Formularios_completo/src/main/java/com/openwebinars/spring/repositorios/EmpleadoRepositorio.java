package com.openwebinars.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.openwebinars.spring.modelo.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
	
	List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContains(
			String nombre, String email, String telefono);
	
	//misma consulta pero con JPQL
	@Query("select e from Empleado e where lower(e.nombre) like %?1% or lower(e.email) like %?1% or "
			+ "lower(e.telefono) like %?1%")
	List<Empleado> encuentraPorNombreEmailOTelefono(String cadena);
}
