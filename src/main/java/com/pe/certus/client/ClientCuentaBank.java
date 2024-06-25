package com.pe.certus.client;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.certus.dto.CuentaDto;
import java.util.HashMap;
import java.util.Map;

@Component
public class ClientCuentaBank {
	public List<CuentaDto> apiCuentaBank() {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet("http://localhost:8080/cuentabank/api/v1/cuenta/todo");
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String responseBody = EntityUtils.toString(entity);
				ObjectMapper objectMapper = new ObjectMapper();
				List<CuentaDto> listCuentaDto = objectMapper.readValue(responseBody, new TypeReference<List<CuentaDto>>() {
				});
				return listCuentaDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Object> apiCuentaBankPages(int pagina) {

		// The base URL for the API
		String baseUrl = "http://localhost:8080/cuentabank/api/v1/cuenta";

		// Construct the URL with the pagina query parameter
		String urlWithPagination = baseUrl + "?pagina=" + pagina;

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet(urlWithPagination);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String responseBody = EntityUtils.toString(entity);
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> responseMap = objectMapper.readValue(responseBody,
						new TypeReference<Map<String, Object>>() {
						});

				List<CuentaDto> listCuentaDto = objectMapper.convertValue(responseMap.get("content"),
						new TypeReference<List<CuentaDto>>() {
						});
				int totalPages = (int) responseMap.get("totalPages");

				Map<String, Object> result = new HashMap<>();
				result.put("content", listCuentaDto);
				result.put("totalPages", (totalPages));

				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
