package com.pe.certus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chr_emplcodigo")
	private Long id;

	@NotEmpty
	@Column(name = "vch_emplpaterno")
	private String paterno;

	@NotEmpty
	@Column(name = "vch_emplmaterno")
	private String materno;

	@NotEmpty
	@Column(name = "vch_emplnombre")
	private String nombres;

	@NotEmpty
	@Column(name = "vch_emplusuario")
	private String usuario;

	@NotEmpty
	@Column(name = "vch_emplclave")
	private String password;

	@Column(name = "chr_emplestado")
	private Boolean estado;

	public Empleado() {
		super();
	}

	public Empleado(Long id, String paterno, String materno, String nombres, String usuario, String password,
			Boolean estado) {
		super();
		this.id = id;
		this.paterno = paterno;
		this.materno = materno;
		this.nombres = nombres;
		this.usuario = usuario;
		this.password = password;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Empleados [id=" + id + ", paterno=" + paterno + ", materno=" + materno + ", nombres=" + nombres
				+ ", usuario=" + usuario + ", estado=" + estado + "]";
	}
}
