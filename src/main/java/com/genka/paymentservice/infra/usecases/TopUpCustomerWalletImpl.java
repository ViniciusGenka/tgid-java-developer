package com.genka.paymentservice.infra.usecases;

import com.genka.paymentservice.application.exceptions.EntityNotFoundException;
import com.genka.paymentservice.application.gateways.TransactionDatabaseGateway;
import com.genka.paymentservice.application.gateways.WalletDatabaseGateway;
import com.genka.paymentservice.application.services.EmailService;
import com.genka.paymentservice.application.services.inputs.SendEmailInput;
import com.genka.paymentservice.application.usecases.TopUpCustomerWallet;
import com.genka.paymentservice.application.usecases.inputs.TopUpCustomerWalletInput;
import com.genka.paymentservice.domain.entities.Transaction;
import com.genka.paymentservice.domain.entities.Wallet;
import com.genka.paymentservice.domain.entities.WalletId;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;
import com.genka.paymentservice.domain.entities.enums.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TopUpCustomerWalletImpl implements TopUpCustomerWallet {
    private final WalletDatabaseGateway walletDatabaseGateway;
    private final TransactionDatabaseGateway transactionDatabaseGateway;
    private final EmailService emailService;

    public TopUpCustomerWalletImpl(WalletDatabaseGateway walletDatabaseGateway, TransactionDatabaseGateway transactionDatabaseGateway, EmailService emailService) {
        this.walletDatabaseGateway = walletDatabaseGateway;
        this.transactionDatabaseGateway = transactionDatabaseGateway;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public TransactionOutput execute(TopUpCustomerWalletInput topUpCustomerWalletInput) {
        WalletId walletId = WalletId.builder()
                .companyId(topUpCustomerWalletInput.getCompanyId())
                .customerId(topUpCustomerWalletInput.getCustomerId())
                .build();
        Wallet wallet = this.walletDatabaseGateway.findWalletById(walletId).orElseThrow(() -> new EntityNotFoundException("Wallet with customer id " + topUpCustomerWalletInput.getCustomerId() + " and company id " + topUpCustomerWalletInput.getCompanyId() + " not found"));
        wallet.topUp(topUpCustomerWalletInput.getValue());
        Transaction topUpTransaction = Transaction.builder()
                .wallet(wallet)
                .type(TransactionType.TOP_UP)
                .value(topUpCustomerWalletInput.getValue())
                .build();
        this.walletDatabaseGateway.saveWallet(wallet);
        Transaction savedTopUpTransaction = this.transactionDatabaseGateway.saveTransaction(topUpTransaction);
        SendEmailInput sendEmailInput = SendEmailInput.builder()
                .to(wallet.getCustomer().getEmail())
                .subject("Top up transaction has been processed successfully")
                .text("You successfully topped up the amount of " + topUpCustomerWalletInput.getValue() + " into your " + wallet.getId() + " wallet")
                .build();
        this.emailService.execute(sendEmailInput);
        return TransactionOutput.mapFromEntity(savedTopUpTransaction);
    }
}
