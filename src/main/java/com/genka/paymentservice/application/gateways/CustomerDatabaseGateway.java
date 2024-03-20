package com.genka.paymentservice.application.gateways;

import com.genka.paymentservice.domain.entities.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerDatabaseGateway {
    Customer saveCustomer(Customer customer);
    Optional<Customer> findCustomerById(UUID customerId);
    Optional<Customer> findCustomerByCpf(String cpf);
}
