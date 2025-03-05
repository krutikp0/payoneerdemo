package com.example.payoneer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayoneerService {

    @Value("${payoneer.client-id}")
    private String clientId;

    @Value("${payoneer.client-secret}")
    private String clientSecret;

    @Value("${payoneer.api-url}")
    private String payoneerApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String createPayment(String payeeId, Double amount) {
        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAccessToken());
        headers.set("Content-Type", "application/json");

        // Prepare request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("payee_id", payeeId);
        requestBody.put("amount", amount);
        requestBody.put("currency", "USD");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Send request
        ResponseEntity<String> response = restTemplate.exchange(
                payoneerApiUrl, HttpMethod.POST, entity, String.class
        );

        return response.getBody();
    }

    private String getAccessToken() {
        // Simulating an access token (in real case, get it from Payoneer)
        return "YOUR_ACCESS_TOKEN";
    }
}
