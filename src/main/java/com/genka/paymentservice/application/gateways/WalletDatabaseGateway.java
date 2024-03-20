package com.genka.paymentservice.application.gateways;

import com.genka.paymentservice.domain.entities.Wallet;
import com.genka.paymentservice.domain.entities.WalletId;

import java.util.Optional;

public interface WalletDatabaseGateway {
    Wallet saveWallet(Wallet wallet);
    Optional<Wallet> findWalletById(WalletId walletId);
}
