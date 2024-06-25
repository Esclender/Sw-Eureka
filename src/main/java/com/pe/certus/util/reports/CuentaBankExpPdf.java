package com.pe.certus.util.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pe.certus.dto.CuentaDto;

public class CuentaBankExpPdf {

	private List<CuentaDto> listaCuenta;

	public CuentaBankExpPdf(List<CuentaDto> listaCuenta) {
		super();
		this.listaCuenta = listaCuenta;
	}

	private void cabeceraTable(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.BLACK);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);
		
		celda.setPhrase(new Phrase("Código", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Moneda", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Sucursal", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Empleado", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Cliente", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Saldo", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Creación", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Movimiento", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Estado", fuente));
		tabla.addCell(celda);
	}
	
	private void datosTabla(PdfPTable tabla) {
		for (CuentaDto cuentaDto: listaCuenta) {
			tabla.addCell(String.valueOf(cuentaDto.getCodigoCuenta()));
			tabla.addCell(cuentaDto.getMoneda());
			tabla.addCell(cuentaDto.getSucursal());
			tabla.addCell(cuentaDto.getEmpleado());
			tabla.addCell(cuentaDto.getCliente());
			tabla.addCell(String.valueOf(cuentaDto.getCuentaSaldo()));
			tabla.addCell(String.valueOf(cuentaDto.getFechaCreacion()));
			tabla.addCell(cuentaDto.getTipoMovimiento());
			tabla.addCell(cuentaDto.getEstado());
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLACK);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Cuentas", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(9);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] {1.9f,2f,2.9f,2.5f,2.5f,2.1f,2.5f,2.9f,2.1f});
		tabla.setWidthPercentage(110);
		
		cabeceraTable(tabla);
		datosTabla(tabla);
		
		documento.add(tabla);
		documento.close();
	}
}
