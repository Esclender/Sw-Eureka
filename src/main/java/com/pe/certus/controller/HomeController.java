package com.pe.certus.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pe.certus.entity.Empleado;
import com.pe.certus.repository.EmpleadoRepository;

@Controller
@RequestMapping({"/home"})
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@GetMapping
	public String home(Authentication auth, HttpSession session) {
		String user = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Empleado usuario = empleadoRepository.findByUsuario(user);
			log.info("Usuario {}", usuario);
			session.setAttribute("usuario", usuario);
			
		}
		
		return "home";
	}
}
