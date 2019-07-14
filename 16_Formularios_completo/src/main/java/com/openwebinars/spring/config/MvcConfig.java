package com.openwebinars.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Para cuando solo necesitemos un controlador que nos redireccione a una plantilla usamos esta clase de conf
 * Añade algo asi como un controller implicito para la vista que necesitamos
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/login");
	}
	
}
