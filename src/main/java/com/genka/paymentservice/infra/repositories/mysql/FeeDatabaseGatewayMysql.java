package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.application.gateways.FeeDatabaseGateway;
import com.genka.paymentservice.domain.entities.Company;
import com.genka.paymentservice.domain.entities.Fee;
import com.genka.paymentservice.domain.entities.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class FeeDatabaseGatewayMysql implements FeeDatabaseGateway {
    private final FeeRepositoryMysql feeRepository;

    FeeDatabaseGatewayMysql(FeeRepositoryMysql feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public Fee saveFee(Fee fee) {
        return this.feeRepository.save(fee);
    }

    @Override
    public List<Fee> findFeeByCompanyAndTransactionType(Company company, TransactionType transactionType) {
        return this.feeRepository.findAllByCompanyAndTransactionType(company, transactionType);
    }
}
