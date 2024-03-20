package com.genka.paymentservice.infra.services;

import com.genka.paymentservice.application.services.WebhookTransactionNotificationService;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookTransactionNotificationServiceImpl implements WebhookTransactionNotificationService {
    private final RestTemplate restTemplate;

    public WebhookTransactionNotificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendTransactionNotification(TransactionOutput transaction, String webhookUrl) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<TransactionOutput> requestEntity = new HttpEntity<>(transaction, headers);
            restTemplate.exchange(webhookUrl, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            System.err.println("Failed to send transaction notification: " + e.getMessage());
        }
    }
}
