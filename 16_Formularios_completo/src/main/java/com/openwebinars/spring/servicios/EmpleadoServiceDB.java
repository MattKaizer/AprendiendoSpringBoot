package com.openwebinars.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.openwebinars.spring.modelo.Empleado;
import com.openwebinars.spring.repositorios.EmpleadoRepositorio;

@Primary
@Service("empleadoServiceDB")
public class EmpleadoServiceDB implements EmpleadoService {
	
	@Autowired
	private EmpleadoRepositorio repositorio;

	@Override
	public Empleado add(Empleado e) {
		// TODO Auto-generated method stub
		return repositorio.save(e);
	}

	@Override
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Empleado findById(long id) {
		// TODO Auto-generated method stub
		//para evitar errores de tipo nullPointerException usamos el orElse, para que si no encuentra el id, devuelva null
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Empleado edit(Empleado e) {
		// TODO Auto-generated method stub
		return repositorio.save(e);
	}

}
