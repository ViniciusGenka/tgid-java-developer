package com.genka.paymentservice.infra.usecases;

import com.genka.paymentservice.application.exceptions.EntityNotFoundException;
import com.genka.paymentservice.application.gateways.CompanyDatabaseGateway;
import com.genka.paymentservice.application.gateways.CustomerDatabaseGateway;
import com.genka.paymentservice.application.gateways.WalletDatabaseGateway;
import com.genka.paymentservice.application.usecases.CreateCustomerWallet;
import com.genka.paymentservice.application.usecases.inputs.CreateCustomerWalletInput;
import com.genka.paymentservice.domain.entities.Company;
import com.genka.paymentservice.domain.entities.Customer;
import com.genka.paymentservice.domain.entities.Wallet;
import com.genka.paymentservice.domain.entities.WalletId;
import com.genka.paymentservice.application.usecases.outputs.WalletOutput;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerWalletImpl implements CreateCustomerWallet {
    private final CustomerDatabaseGateway customerDatabaseGateway;
    private final CompanyDatabaseGateway companyDatabaseGateway;
    private final WalletDatabaseGateway walletDatabaseGateway;

    public CreateCustomerWalletImpl(CustomerDatabaseGateway customerDatabaseGateway, CompanyDatabaseGateway companyDatabaseGateway, WalletDatabaseGateway walletDatabaseGateway) {
        this.customerDatabaseGateway = customerDatabaseGateway;
        this.companyDatabaseGateway = companyDatabaseGateway;
        this.walletDatabaseGateway = walletDatabaseGateway;
    }

    @Override
    public WalletOutput execute(CreateCustomerWalletInput createCustomerWalletInput) {
        Customer customer = this.customerDatabaseGateway.findCustomerById(createCustomerWalletInput.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer with id " + createCustomerWalletInput.getCustomerId() + " not found"));
        Company company = this.companyDatabaseGateway.findCompanyById(createCustomerWalletInput.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company with id " + createCustomerWalletInput.getCompanyId() + " not found"));
        WalletId walletId = WalletId.builder()
                .customerId(customer.getId())
                .companyId(company.getId())
                .build();
        Wallet wallet = Wallet.builder()
                .id(walletId)
                .customer(customer)
                .company(company)
                .build();
        Wallet savedWallet = this.walletDatabaseGateway.saveWallet(wallet);
        return WalletOutput.mapFromEntity(savedWallet);
    }
}
