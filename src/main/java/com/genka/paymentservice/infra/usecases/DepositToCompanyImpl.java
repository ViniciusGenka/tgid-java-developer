package com.genka.paymentservice.infra.usecases;

import com.genka.paymentservice.application.exceptions.EntityNotFoundException;
import com.genka.paymentservice.application.gateways.*;
import com.genka.paymentservice.application.services.EmailService;
import com.genka.paymentservice.application.services.WebhookTransactionNotificationService;
import com.genka.paymentservice.application.services.inputs.SendEmailInput;
import com.genka.paymentservice.application.usecases.DepositToCompany;
import com.genka.paymentservice.application.usecases.inputs.DepositToCompanyInput;
import com.genka.paymentservice.domain.entities.*;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;
import com.genka.paymentservice.domain.entities.enums.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepositToCompanyImpl implements DepositToCompany {

    private final TransactionDatabaseGateway transactionDatabaseGateway;
    private final WalletDatabaseGateway walletDatabaseGateway;
    private final CustomerDatabaseGateway customerDatabaseGateway;
    private final CompanyDatabaseGateway companyDatabaseGateway;
    private final FeeDatabaseGateway feeDatabaseGateway;
    private final WebhookTransactionNotificationService webhookTransactionNotificationService;
    private final EmailService emailService;

    public DepositToCompanyImpl(TransactionDatabaseGateway transactionDatabaseGateway, WalletDatabaseGateway walletDatabaseGateway, CustomerDatabaseGateway customerDatabaseGateway, CompanyDatabaseGateway companyDatabaseGateway, FeeDatabaseGateway feeDatabaseGateway, WebhookTransactionNotificationService webhookTransactionNotificationService, EmailService emailService) {
        this.transactionDatabaseGateway = transactionDatabaseGateway;
        this.walletDatabaseGateway = walletDatabaseGateway;
        this.customerDatabaseGateway = customerDatabaseGateway;
        this.companyDatabaseGateway = companyDatabaseGateway;
        this.feeDatabaseGateway = feeDatabaseGateway;
        this.webhookTransactionNotificationService = webhookTransactionNotificationService;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public TransactionOutput execute(DepositToCompanyInput depositToCompanyInput) {
        Customer customer = this.customerDatabaseGateway.findCustomerById(depositToCompanyInput.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer with id " + depositToCompanyInput.getCustomerId() + " not found"));
        Company company = this.companyDatabaseGateway.findCompanyById(depositToCompanyInput.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company with id " + depositToCompanyInput.getCompanyId() + " not found"));
        WalletId walletId = WalletId.builder()
                .companyId(depositToCompanyInput.getCompanyId())
                .customerId(depositToCompanyInput.getCustomerId())
                .build();
        Wallet wallet = this.walletDatabaseGateway.findWalletById(walletId).orElseThrow(() -> new EntityNotFoundException("Wallet with customer id " + customer.getId() + " and company id " + company.getId() + " not found"));
        List<Fee> applicableFees = this.feeDatabaseGateway.findFeeByCompanyAndTransactionType(company, TransactionType.DEPOSIT);
        BigDecimal applicableFeesValue = applicableFees.stream().map(Fee::getValue).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(depositToCompanyInput.getValue());
        BigDecimal transactionTotalValue = depositToCompanyInput.getValue().add(applicableFeesValue);
        wallet.withdraw(transactionTotalValue);
        company.deposit(transactionTotalValue);
        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .type(TransactionType.DEPOSIT)
                .value(transactionTotalValue)
                .build();
        this.walletDatabaseGateway.saveWallet(wallet);
        this.companyDatabaseGateway.saveCompany(company);
        Transaction savedTransaction = this.transactionDatabaseGateway.saveTransaction(transaction);
        TransactionOutput transactionOutput = TransactionOutput.mapFromEntity(savedTransaction);
        this.webhookTransactionNotificationService.sendTransactionNotification(transactionOutput, company.getWebhookUrl());
        SendEmailInput sendEmailInput = SendEmailInput.builder()
                .to(customer.getEmail())
                .subject("Deposit transaction has been processed successfully")
                .text("You successfully deposited the amount of " + transactionTotalValue + " into company " + company.getId() + " account")
                .build();
        this.emailService.execute(sendEmailInput);
        return transactionOutput;
    }
}
