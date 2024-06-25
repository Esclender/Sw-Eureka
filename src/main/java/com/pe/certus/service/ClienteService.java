package com.pe.certus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pe.certus.entity.Cliente;

public interface ClienteService {

	public List<Cliente> listarCliente();

	public Page<Cliente> obtenerPaginas(Pageable pageable);

	public Cliente getIdCliente(Long id);

	public void crearCliente(Cliente cliente);

	public void actualizarCliente(Cliente cliente);

	public void eliminarCliente(Long id);

	public boolean isDniAlreadyInUse(String dni);

	public boolean isEmailAlreadyInUse(String email);
}
