package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.application.gateways.WalletDatabaseGateway;
import com.genka.paymentservice.domain.entities.Wallet;
import com.genka.paymentservice.domain.entities.WalletId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class WalletDatabaseGatewayMysql implements WalletDatabaseGateway {
    private final WalletRepositoryMysql walletRepository;

    WalletDatabaseGatewayMysql(WalletRepositoryMysql walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return this.walletRepository.save(wallet);
    }

    @Override
    public Optional<Wallet> findWalletById(WalletId walletId) {
        return this.walletRepository.findById(walletId);
    }
}
