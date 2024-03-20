package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.application.gateways.TransactionDatabaseGateway;
import com.genka.paymentservice.domain.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
class TransactionDatabaseGatewayMysql implements TransactionDatabaseGateway {
    private final TransactionRepositoryMysql transactionRepository;

    TransactionDatabaseGatewayMysql(TransactionRepositoryMysql transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
}
