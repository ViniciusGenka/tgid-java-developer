package com.genka.paymentservice.infra.repositories.mysql;

import com.genka.paymentservice.application.gateways.CustomerDatabaseGateway;
import com.genka.paymentservice.domain.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
class CustomerDatabaseGatewayMysql implements CustomerDatabaseGateway {

    private final CustomerRepositoryMysql customerRepository;

    CustomerDatabaseGatewayMysql(CustomerRepositoryMysql customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(UUID customerId) {
        return this.customerRepository.findById(customerId);
    }

    @Override
    public Optional<Customer> findCustomerByCpf(String cpf) {
        return this.customerRepository.findCustomerByCpf(cpf);
    }
}
