package com.genka.paymentservice.infra.usecases;

import com.genka.paymentservice.application.exceptions.EntityNotFoundException;
import com.genka.paymentservice.application.gateways.*;
import com.genka.paymentservice.application.services.EmailService;
import com.genka.paymentservice.application.services.inputs.SendEmailInput;
import com.genka.paymentservice.application.usecases.WithdrawFromCompany;
import com.genka.paymentservice.application.usecases.inputs.WithdrawFromCompanyInput;
import com.genka.paymentservice.domain.entities.*;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;
import com.genka.paymentservice.domain.entities.enums.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WithdrawFromCompanyImpl implements WithdrawFromCompany {
    private final TransactionDatabaseGateway transactionDatabaseGateway;
    private final WalletDatabaseGateway walletDatabaseGateway;
    private final CustomerDatabaseGateway customerDatabaseGateway;
    private final CompanyDatabaseGateway companyDatabaseGateway;
    private final FeeDatabaseGateway feeDatabaseGateway;
    private final EmailService emailService;

    public WithdrawFromCompanyImpl(TransactionDatabaseGateway transactionDatabaseGateway, WalletDatabaseGateway walletDatabaseGateway, CustomerDatabaseGateway customerDatabaseGateway, CompanyDatabaseGateway companyDatabaseGateway, FeeDatabaseGateway feeDatabaseGateway, EmailService emailService) {
        this.transactionDatabaseGateway = transactionDatabaseGateway;
        this.walletDatabaseGateway = walletDatabaseGateway;
        this.customerDatabaseGateway = customerDatabaseGateway;
        this.companyDatabaseGateway = companyDatabaseGateway;
        this.feeDatabaseGateway = feeDatabaseGateway;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public TransactionOutput execute(WithdrawFromCompanyInput withdrawFromCompanyInput) {
        Customer customer = this.customerDatabaseGateway.findCustomerById(withdrawFromCompanyInput.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer with id " + withdrawFromCompanyInput.getCustomerId() + " not found"));
        Company company = this.companyDatabaseGateway.findCompanyById(withdrawFromCompanyInput.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company with id " + withdrawFromCompanyInput.getCompanyId() + " not found"));
        WalletId walletId = WalletId.builder()
                .companyId(withdrawFromCompanyInput.getCompanyId())
                .customerId(withdrawFromCompanyInput.getCustomerId())
                .build();
        Wallet wallet = this.walletDatabaseGateway.findWalletById(walletId).orElseThrow(() -> new EntityNotFoundException("Wallet with customer id " + withdrawFromCompanyInput.getCustomerId() + " and company id " + withdrawFromCompanyInput.getCompanyId() + " not found"));
        List<Fee> applicableFees = this.feeDatabaseGateway.findFeeByCompanyAndTransactionType(company, TransactionType.WITHDRAW);
        BigDecimal applicableFeesValue = applicableFees.stream().map(Fee::getValue).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(withdrawFromCompanyInput.getValue());
        BigDecimal transactionTotalValue = withdrawFromCompanyInput.getValue().subtract(applicableFeesValue);
        company.withdraw(transactionTotalValue);
        wallet.deposit(transactionTotalValue);
        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .type(TransactionType.WITHDRAW)
                .value(transactionTotalValue)
                .build();
        this.walletDatabaseGateway.saveWallet(wallet);
        this.companyDatabaseGateway.saveCompany(company);
        Transaction savedTransaction = this.transactionDatabaseGateway.saveTransaction(transaction);
        SendEmailInput sendEmailInput = SendEmailInput.builder()
                .to(customer.getEmail())
                .subject("Withdraw transaction has been processed successfully")
                .text("You successfully withdrew the amount of " + transactionTotalValue + " from company " + company.getId() + " account")
                .build();
        this.emailService.execute(sendEmailInput);
        return TransactionOutput.mapFromEntity(savedTransaction);
    }
}
