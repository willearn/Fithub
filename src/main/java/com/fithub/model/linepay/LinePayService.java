package com.fithub.model.linepay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LinePayService {

	private final String LINE_PAY_API_URL = "https://sandbox-api-pay.line.me/v3/payments/request";

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<String> createLinePayOrder(String signature, String nonce, String form) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("X-LINE-ChannelId", "2000694294");
		headers.set("X-LINE-Authorization-Nonce", nonce);
		headers.set("X-LINE-Authorization", signature);

		HttpEntity<String> entity = new HttpEntity<>(form, headers);

		ResponseEntity<String> response = restTemplate.exchange(LINE_PAY_API_URL, HttpMethod.POST, entity,
				String.class);

		return response;
	}
}
