package com.pe.certus.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.certus.entity.Cliente;
import com.pe.certus.service.ClienteService;

@Controller
@RequestMapping("/")
public class ClienteController {

	private final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/views/cliente")
	public String listar(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			Model model) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Cliente> clientes = clienteService.obtenerPaginas(pageable);
		log.info("Lista de Clientes {}", clientes);

		model.addAttribute("clientes", clientes.getContent());
		model.addAttribute("clientesPage", clientes);

		return "/views/cliente/listar";
	}

	@GetMapping("/views/cliente/create")
	public String create(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "/views/cliente/create";
	}

	@PostMapping("/views/cliente/save")
	public String guardar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attribute) {
		log.info("Objeto {}", cliente);
		if (result.hasErrors()) {
			return "/views/cliente/create";
		}

		if (clienteService.isDniAlreadyInUse(cliente.getDni())) {
			result.rejectValue("dni", "error.user", "El Dni ya fue registrado");
			return "/views/cliente/create";
		}

		if (clienteService.isEmailAlreadyInUse(cliente.getCorreo())) {
			result.rejectValue("correo", "error.user", "El Correo ya fue registrado");
			return "/views/cliente/create";
		}

		clienteService.crearCliente(cliente);
		attribute.addFlashAttribute("success", "El Cliente se registro con éxito");
		return "redirect:/views/cliente";
	}

	@PostMapping("/views/cliente/actualizar")
	public String actualizar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attribute) {
		log.info("Objeto {}", cliente);
		if (result.hasErrors()) {
			return "/views/cliente/actualizar";
		}

		clienteService.actualizarCliente(cliente);
		attribute.addFlashAttribute("success", "El Cliente se actualizo con éxito");
		return "redirect:/views/cliente";
	}

	@GetMapping("/views/cliente/edit/{id}")
	public String editar(@PathVariable Long id, Model model, RedirectAttributes attribute) {
		Cliente cliente = clienteService.getIdCliente(id);
		if (cliente == null) {
			attribute.addFlashAttribute("error", "Error: El ID del cliente no exite");
			return "redirect:/views/cliente";
		}
		log.info("Objeto {}", cliente);
		model.addAttribute("cliente", cliente);
		return "/views/cliente/edit";
	}

	@GetMapping("/views/cliente/delete/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes attribute) {
		Cliente cliente = clienteService.getIdCliente(id);
		if (cliente == null) {
			attribute.addFlashAttribute("error", "Error: El ID del cliente no exite");
			return "redirect:/views/cliente";
		}
		log.info("Objeto eliminado {}", cliente);
		clienteService.eliminarCliente(id);
		return "redirect:/views/cliente";
	}

}
