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
import com.pe.certus.client.ClientCuentaBank;
import com.pe.certus.dto.CuentaDto;
import com.pe.certus.util.Utilitario;
import com.pe.certus.util.reports.CuentaBankExpPdf;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class CuentaBankController {

	private final Logger log = LoggerFactory.getLogger(CuentaBankController.class);

	public static int[] numberToArray(int n) {
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = i;
		}
		return result;
	}

	@Autowired
	private ClientCuentaBank clientCuentaBank;

	@GetMapping("/views/cuenta")
	public String listarCuentas(Model model,
			@RequestParam(defaultValue = "0") int pagina) {
		Map<String, Object> cuentaDta = clientCuentaBank.apiCuentaBankPages(pagina);
		System.out.println(cuentaDta);

		List<CuentaDto> listCuenta = (List<CuentaDto>) cuentaDta.get("content");
		int totalPages = (int) cuentaDta.get("totalPages");

		model.addAttribute("cuentas", listCuenta);
		model.addAttribute("currentPage", pagina);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pagesArray", numberToArray(totalPages));
		return "/views/cuenta/listar";
	}

	@GetMapping("/export-cuentas-pdf")
	public void exportarCuentasPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		String fecha = Utilitario.getFormatDate();

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Cuentas_" + fecha + ".pdf";

		response.setHeader(cabecera, valor);

		CuentaBankExpPdf export = new CuentaBankExpPdf(clientCuentaBank.apiCuentaBank());
		export.exportar(response);
	}
}
