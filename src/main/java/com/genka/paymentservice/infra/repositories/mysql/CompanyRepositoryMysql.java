package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.domain.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepositoryMysql extends JpaRepository<Company, UUID> {
    Optional<Company> findCompanyByCnpj(String cnpj);
}
