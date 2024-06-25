package com.pe.certus.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CuentaDto {
	private int codigoCuenta;
	private String moneda;
	private String sucursal;
	private String empleado;
	private String cliente;
	private double cuentaSaldo;
	private Date fechaCreacion;
	private String tipoMovimiento;
	private String estado;

	public CuentaDto() {
		super();
	}

	public CuentaDto(int codigoCuenta, String moneda, String sucursal, String empleado, String cliente,
			double cuentaSaldo, Date fechaCreacion, String tipoMovimiento, String estado) {
		super();
		this.codigoCuenta = codigoCuenta;
		this.moneda = moneda;
		this.sucursal = sucursal;
		this.empleado = empleado;
		this.cliente = cliente;
		this.cuentaSaldo = cuentaSaldo;
		this.fechaCreacion = fechaCreacion;
		this.tipoMovimiento = tipoMovimiento;
		this.estado = estado;
	}

	public int getCodigoCuenta() {
		return codigoCuenta;
	}

	public void setCodigoCuenta(int codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getCuentaSaldo() {
		return cuentaSaldo;
	}

	public void setCuentaSaldo(double cuentaSaldo) {
		this.cuentaSaldo = cuentaSaldo;
	}

	public String getFechaCreacion() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(fechaCreacion);
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CuentaDto {" +
				"\n    codigoCuenta: " + codigoCuenta + "," +
				"\n    moneda: " + moneda + "," +
				"\n    sucursal: " + sucursal + "," +
				"\n    empleado: " + empleado + "," +
				"\n    cliente: " + cliente + "," +
				"\n    cuentaSaldo: " + cuentaSaldo + "," +
				"\n    fechaCreacion: " + fechaCreacion + "," +
				"\n    tipoMovimiento: " + tipoMovimiento + "," +
				"\n    estado: " + estado +
				"\n}";
	}
}
