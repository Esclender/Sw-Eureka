package com.pe.certus.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pe.certus.entity.Cliente;
import com.pe.certus.entity.CustomUserDetails;
import com.pe.certus.entity.Empleado;
import com.pe.certus.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Empleado> listarEmpleado() {
		return empleadoRepository.findAll();
	}

	@Override
	public Empleado getIdEmpleado(Long id) {
		Empleado empleado = empleadoRepository.findById(id).orElse(null);
		return empleado;
	}

	@Override
	public void crearEmpleado(Empleado empleado) {
		Empleado e = new Empleado();
		e.setId(empleado.getId());
		e.setPaterno(empleado.getPaterno());
		e.setMaterno(empleado.getMaterno());
		e.setNombres(empleado.getNombres());
		e.setUsuario(empleado.getUsuario());
		e.setPassword(passwordEncoder.encode(empleado.getPassword()));
		if (empleado.getEstado() == null) {
			e.setEstado(true);
		} else {
			e.setEstado(empleado.getEstado());
		}
		empleadoRepository.save(e);
	}

	@Override
	public void eliminarEmpleado(Long id) {
		empleadoRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleado empleado = empleadoRepository.findByUsuario(username); // Implement this method in your repository
		if (empleado == null) {
			throw new UsernameNotFoundException("Usuario esta INACTIVO!");
		}
		return new CustomUserDetails(
				empleado.getUsuario(),
				empleado.getPassword(),
				empleado.getEstado(),
				Collections.emptyList());
	}

	@Override
	public void actualizarEmpleado(Empleado empleado) {
		Empleado objToUpdate = empleadoRepository.getReferenceById(empleado.getId());
		BeanUtils.copyProperties(empleado, objToUpdate);
		empleadoRepository.save(objToUpdate);
	}

	@Override
	public Page<Empleado> obtenerPaginas(Pageable pageable) {
		return empleadoRepository.findAll(pageable);
	}
}
