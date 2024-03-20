package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepositoryMysql extends JpaRepository<Customer, UUID> {
    Optional<Customer> findCustomerByCpf(String cpf);
}
