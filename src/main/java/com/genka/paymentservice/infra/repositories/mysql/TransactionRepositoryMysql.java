package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepositoryMysql extends JpaRepository<Transaction, UUID> {
}
