package com.pe.certus.util.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
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
import com.pe.certus.dto.MovimientoDto;

public class MovimientoBankExpPdf {
	
	private List<MovimientoDto> listaMovimiento;
	
	public MovimientoBankExpPdf(List<MovimientoDto> listaMovimiento) {
		super();
		this.listaMovimiento = listaMovimiento;
	}

	private void cabeceraTable(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.BLACK);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);
		
		celda.setPhrase(new Phrase("Código", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Numero", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Creacion", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Empleado", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Movimiento", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Importe", fuente));
		tabla.addCell(celda);
	}
	
	private void datosTabla(PdfPTable tabla) {
		
		for(MovimientoDto movimientoDto: listaMovimiento) {
			tabla.addCell(String.valueOf(movimientoDto.getCodigoMovimiento()));
			tabla.addCell(movimientoDto.getNumero());
			tabla.addCell(String.valueOf(movimientoDto.getFechaCreacion()));
			tabla.addCell(movimientoDto.getEmpleado());
			tabla.addCell(movimientoDto.getTipoMovimiento());
			tabla.addCell(String.valueOf(movimientoDto.getMovimientoimporte()));
		}
	}
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLACK);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Movimientos", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(6);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[]{1.9f,2f,2.9f,2.5f,2.5f,2.1f});
		tabla.setWidthPercentage(110);
		
		cabeceraTable(tabla);
		datosTabla(tabla);
		
		documento.add(tabla);
		documento.close();
	}
	

}
