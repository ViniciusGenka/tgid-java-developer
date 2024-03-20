package com.genka.paymentservice.infra.usecases;

import com.genka.paymentservice.application.gateways.CompanyDatabaseGateway;
import com.genka.paymentservice.application.usecases.CreateCompanyAccount;
import com.genka.paymentservice.application.usecases.inputs.CreateCompanyAccountInput;
import com.genka.paymentservice.domain.entities.Company;
import com.genka.paymentservice.application.usecases.outputs.CompanyOutput;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyAccountImpl implements CreateCompanyAccount {
    private final CompanyDatabaseGateway companyDatabaseGateway;

    public CreateCompanyAccountImpl(CompanyDatabaseGateway companyDatabaseGateway) {
        this.companyDatabaseGateway = companyDatabaseGateway;
    }

    @Override
    public CompanyOutput execute(CreateCompanyAccountInput createCompanyAccountInput) {
        this.companyDatabaseGateway.findCompanyByCnpj(createCompanyAccountInput.getCnpj())
                .ifPresent(companyAlreadyExists -> {
                    throw new IllegalArgumentException("Company with CNPJ " + companyAlreadyExists.getCnpj() + " already exists");
                });
        Company company = Company.builder()
                .cnpj(createCompanyAccountInput.getCnpj())
                .webhookUrl(createCompanyAccountInput.getWebhookUrl())
                .build();
        Company savedCompany = this.companyDatabaseGateway.saveCompany(company);
        return CompanyOutput.mapFromEntity(savedCompany);
    }
}
