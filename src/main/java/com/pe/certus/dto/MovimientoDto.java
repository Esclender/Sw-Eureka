package com.pe.certus.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimientoDto {
	private int codigoMovimiento;
	private  String numero;
	private Date fechaCreacion;
	private String empleado;
	private String tipoMovimiento;
	private double movimientoimporte;
	
	public MovimientoDto() {
		super();
	}

	public MovimientoDto(int codigoMovimiento, String numero, Date fechaCreacion, String empleado,
			String tipoMovimiento, double movimientoimporte) {
		super();
		this.codigoMovimiento = codigoMovimiento;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.empleado = empleado;
		this.tipoMovimiento = tipoMovimiento;
		this.movimientoimporte = movimientoimporte;
	}

	public int getCodigoMovimiento() {
		return codigoMovimiento;
	}

	public void setCodigoMovimiento(int codigoMovimiento) {
		this.codigoMovimiento = codigoMovimiento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaCreacion() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(fechaCreacion);
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public double getMovimientoimporte() {
		return movimientoimporte;
	}

	public void setMovimientoimporte(double movimientoimporte) {
		this.movimientoimporte = movimientoimporte;
	}

	@Override
	public String toString() {
		return "MovimientoDto [codigoMovimiento=" + codigoMovimiento + ", numero=" + numero + ", fechaCreacion="
				+ fechaCreacion + ", empleado=" + empleado + ", tipoMovimiento=" + tipoMovimiento
				+ ", movimientoimporte=" + movimientoimporte + "]";
	}
	
	
}
