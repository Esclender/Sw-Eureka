package com.pe.certus.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lowagie.text.DocumentException;
import com.pe.certus.client.ClientMovimientoBank;
import com.pe.certus.dto.MovimientoDto;
import com.pe.certus.util.Utilitario;
import com.pe.certus.util.reports.MovimientoBankExpPdf;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MovimientoBankController {

	private final Logger log = LoggerFactory.getLogger(MovimientoBankController.class);

	public static int[] numberToArray(int n) {
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = i;
		}
		return result;
	}

	@Autowired
	private ClientMovimientoBank clientMovimientoBank;

	@GetMapping("/views/movimiento")
	public String listarMovimientos(Model model,
			@RequestParam(defaultValue = "0") int pagina) {
		Map<String, Object> movimientoData = clientMovimientoBank.apiMovimientoBank(pagina);
		log.info("Objeto {} ", movimientoData);

		List<MovimientoDto> listMovimiento = (List<MovimientoDto>) movimientoData.get("content");
		int totalPages = (int) movimientoData.get("totalPages");

		log.info("Objeto {} ", numberToArray(totalPages));

		model.addAttribute("movimientos", listMovimiento);
		model.addAttribute("currentPage", pagina);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pagesArray", numberToArray(totalPages));

		return "/views/movimiento/listar";
	}

	@GetMapping("/export-movimientos-pdf")
	public String exportarMovimientosPdf(HttpServletResponse response, Model model)
			throws DocumentException, IOException {
		List<MovimientoDto> movimientoData = clientMovimientoBank.apiMovimientoBankAllMovimientos();

		if (movimientoData.isEmpty()) {
			return "redirect:/views/movimiento?showModal=true";
		} else {
			response.setContentType("application/pdf");
			String fecha = Utilitario.getFormatDate();
			String cabecera = "Content-Disposition";
			String valor = "attachment; filename=Movimientos_" + fecha + ".pdf";
			response.setHeader(cabecera, valor);
			MovimientoBankExpPdf export = new MovimientoBankExpPdf(movimientoData);
			export.exportar(response);
			return "redirect:/views/movimiento";
		}
	}

}
