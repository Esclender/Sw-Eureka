package com.pe.certus.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilitario {

	public static String getFormatDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaHoy = dateFormat.format(new Date());
		return fechaHoy;
	}
}