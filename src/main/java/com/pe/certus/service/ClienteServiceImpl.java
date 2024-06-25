package com.pe.certus.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pe.certus.entity.Cliente;
import com.pe.certus.entity.Empleado;
import com.pe.certus.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public boolean isDniAlreadyInUse(String dni) {
		return clienteRepository.existsByDni(dni);
	}

	@Override
	public boolean isEmailAlreadyInUse(String email) {
		return clienteRepository.existsByCorreo(email);
	}

	@Override
	public List<Cliente> listarCliente() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente getIdCliente(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		return cliente;
	}

	@Override
	public void crearCliente(Cliente cliente) {
		Cliente cli = new Cliente();
		cli.setId(cliente.getId());
		cli.setPaterno(cliente.getPaterno());
		cli.setMaterno(cliente.getMaterno());
		cli.setNombres(cliente.getNombres());
		cli.setDni(cliente.getDni());
		cli.setCiudad(cliente.getCiudad());
		cli.setCorreo(cliente.getCorreo());
		if (cliente.getEstado() == null) {
			cli.setEstado(true);
		} else {
			cli.setEstado(cliente.getEstado());
		}
		clienteRepository.save(cli);
	}

	@Override
	public void eliminarCliente(Long id) {
		Cliente cliente = clienteRepository.getReferenceById(id);
		cliente.setEstado(false);
		clienteRepository.save(cliente);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		Cliente objToUpdate = clienteRepository.getReferenceById(cliente.getId());
		BeanUtils.copyProperties(cliente, objToUpdate);
		clienteRepository.save(objToUpdate);
	}

	@Override
	public Page<Cliente> obtenerPaginas(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

}
