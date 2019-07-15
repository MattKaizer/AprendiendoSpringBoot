package com.openwebinars.spring.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.openwebinars.spring.modelo.Empleado;
import com.openwebinars.spring.servicios.EmpleadoServiceInMemory;
import com.openwebinars.spring.upload.storage.I_StorageService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoServiceInMemory servicio;
	@Autowired
	private I_StorageService storageService;
	
	
	@GetMapping({"/", "empleado/list"})
	public String listado(Model model) {
		model.addAttribute("listaEmpleados", servicio.findAll());
		return "list";
	}
	
	@GetMapping("/empleado/new")
	public String nuevoEmpleadoForm(Model model) {
		model.addAttribute("empleadoForm", new Empleado());
		return "form";
	}
	
	@PostMapping("/empleado/new/submit")
	public String nuevoEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") 
	Empleado nuevoEmpleado, BindingResult bindingresult, @RequestParam("file")
	MultipartFile file) {
		
		if (bindingresult.hasErrors()) {
			return "form";
		} else {
			if (!file.isEmpty()) {
				//Logica de almacenamiento de fichero, es mas seguro que trabajar con cadena de caracteres
				String avatar = storageService.store(file, nuevoEmpleado.getId());
				nuevoEmpleado.setImagen(MvcUriComponentsBuilder.fromMethodName(EmpleadoController.class, "serveFile", avatar).build().toString());
			}
			servicio.add(nuevoEmpleado);
			return "redirect:/empleado/list";
		}
	}
	
	@GetMapping("/empleado/edit/{id}")
	public String editEmpleadoForm(@PathVariable long id, Model model) {
		Empleado empleado = servicio.findByID(id);
		if (empleado != null) {
			model.addAttribute("empleadoForm", empleado);
			return "form";
		} else {
			return "redirect:/empleado/new";
		}

	}
	
	@PostMapping("/empleado/edit/submit")
	public String editarEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") 
	Empleado empleado, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "form";
		} else {
		servicio.edit(empleado);
		return "redirect:/empleado/list";
		}
	}
	
	
	/*
	 * @ResponseBody indica que el metodo devolverá un recurso y no una vista
	 * es un envoltorio para un fichero de tipo binario.
	 * @GetMapping: usa una expresion glob, que ademas, asigna el valor a la var filename
	 */
	
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }
	

}
