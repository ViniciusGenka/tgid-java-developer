package com.genka.paymentservice.application.gateways;

import com.genka.paymentservice.domain.entities.Company;
import com.genka.paymentservice.domain.entities.Fee;
import com.genka.paymentservice.domain.entities.enums.TransactionType;

import java.util.List;

public interface FeeDatabaseGateway {
    Fee saveFee(Fee fee);
    List<Fee> findFeeByCompanyAndTransactionType(Company company, TransactionType transactionType);
}
