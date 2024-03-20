package com.genka.paymentservice.application.services;

import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;

public interface WebhookTransactionNotificationService {
    void sendTransactionNotification(TransactionOutput transaction, String webhookUrl);
}
