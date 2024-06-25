package com.pe.certus.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.certus.entity.Empleado;
import com.pe.certus.service.EmpleadoService;

@Controller
@RequestMapping("/")
public class EmpleadoController {

	private final Logger log = LoggerFactory.getLogger(EmpleadoController.class);

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/views/empleado")
	public String listar(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			Model model) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Empleado> empleadosPage = empleadoService.obtenerPaginas(pageable);
		log.info("Lista de Empleados {}", empleadosPage);

		model.addAttribute("empleados", empleadosPage.getContent());
		model.addAttribute("empleadosPage", empleadosPage);

		return "/views/empleado/listar";
	}

	@GetMapping("/views/empleado/create")
	public String create(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "/views/empleado/create";
	}

	@PostMapping("/views/empleado/save")
	public String guardar(@Valid Empleado empleado, BindingResult result, RedirectAttributes attribute) {
		log.info("Objeto {}", empleado);
		if (result.hasErrors()) {
			return "/views/empleado/create";
		}
		empleadoService.crearEmpleado(empleado);
		attribute.addFlashAttribute("success", "El Empleado se registro con éxito");
		return "redirect:/views/empleado";
	}

	@PostMapping("/views/empleado/actualizar")
	public String actualizar(@Valid Empleado empleado, @RequestParam("newPassword") String newPassword,
			BindingResult result, RedirectAttributes attribute) {
		log.info("Objeto {}", empleado);
		if (result.hasErrors()) {
			return "/views/empleado/create";
		}
		if (newPassword != null && !newPassword.isEmpty()) {
			String encryptedPassword = passwordEncoder.encode(newPassword);
			empleado.setPassword(encryptedPassword);
		}
		empleadoService.actualizarEmpleado(empleado);
		attribute.addFlashAttribute("success", "El Empleado se actualizo con éxito");
		return "redirect:/views/empleado";
	}

	@GetMapping("/views/empleado/edit/{id}")
	public String editar(@PathVariable Long id, Model model,
			RedirectAttributes attribute) {
		Empleado empleado = empleadoService.getIdEmpleado(id);
		if (empleado == null) {
			attribute.addFlashAttribute("error", "Error: El ID del empleado no exite");
			return "redirect:/views/empleado";
		}

		log.info("Objeto {}", empleado);
		model.addAttribute("empleado", empleado);
		return "/views/empleado/edit";
	}

	@GetMapping("/views/empleado/delete/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes attribute) {
		Empleado empleado = empleadoService.getIdEmpleado(id);
		if (empleado == null) {
			attribute.addFlashAttribute("error", "Error: El ID del empleado no exite");
			return "redirect:/views/empleado";
		}
		log.info("Objeto eliminado {}", empleado);
		empleadoService.eliminarEmpleado(id);
		return "redirect:/views/cliente";
	}

}
