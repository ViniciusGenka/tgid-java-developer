package com.genka.paymentservice.application.gateways;

import com.genka.paymentservice.domain.entities.Transaction;

public interface TransactionDatabaseGateway {
    Transaction saveTransaction(Transaction transaction);
}
