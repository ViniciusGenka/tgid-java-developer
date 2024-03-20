package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.domain.entities.Company;
import com.genka.paymentservice.domain.entities.Fee;
import com.genka.paymentservice.domain.entities.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeeRepositoryMysql extends JpaRepository<Fee, UUID> {
    List<Fee> findAllByCompanyAndTransactionType(Company company, TransactionType transactionType);
}
