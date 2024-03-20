package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.domain.entities.Wallet;
import com.genka.paymentservice.domain.entities.WalletId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepositoryMysql extends JpaRepository<Wallet, WalletId> {
}
