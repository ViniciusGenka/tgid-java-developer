package com.genka.paymentservice.application.gateways;

import com.genka.paymentservice.domain.entities.Company;

import java.util.Optional;
import java.util.UUID;

public interface CompanyDatabaseGateway {
    Company saveCompany(Company company);
    Optional<Company> findCompanyById(UUID companyId);
    Optional<Company> findCompanyByCnpj(String cnpj);
}
