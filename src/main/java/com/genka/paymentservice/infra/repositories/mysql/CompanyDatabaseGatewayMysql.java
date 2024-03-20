package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.application.gateways.CompanyDatabaseGateway;
import com.genka.paymentservice.domain.entities.Company;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
class CompanyDatabaseGatewayMysql implements CompanyDatabaseGateway {
    private final CompanyRepositoryMysql companyRepository;

    CompanyDatabaseGatewayMysql(CompanyRepositoryMysql companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company saveCompany(Company company) {
        return this.companyRepository.save(company);
    }

    @Override
    public Optional<Company> findCompanyById(UUID companyId) {
        return this.companyRepository.findById(companyId);
    }

    @Override
    public Optional<Company> findCompanyByCnpj(String cnpj) {
        return this.companyRepository.findCompanyByCnpj(cnpj);
    }
}
