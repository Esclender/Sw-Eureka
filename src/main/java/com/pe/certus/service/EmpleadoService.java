package com.pe.certus.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.pe.certus.entity.Empleado;

public interface EmpleadoService extends UserDetailsService {
	public List<Empleado> listarEmpleado();

	public Empleado getIdEmpleado(Long id);

	public void crearEmpleado(Empleado empleado);

	public void actualizarEmpleado(Empleado empleado);

	public void eliminarEmpleado(Long id);

	public Page<Empleado> obtenerPaginas(Pageable pageable);
}
