package com.genka.paymentservice.infra.usecases;

import com.genka.paymentservice.application.gateways.CustomerDatabaseGateway;
import com.genka.paymentservice.application.usecases.CreateCustomerAccount;
import com.genka.paymentservice.application.usecases.inputs.CreateCustomerAccountInput;
import com.genka.paymentservice.domain.entities.Customer;
import com.genka.paymentservice.application.usecases.outputs.CustomerOutput;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerAccountImpl implements CreateCustomerAccount {

    private final CustomerDatabaseGateway customerDatabaseGateway;

    public CreateCustomerAccountImpl(CustomerDatabaseGateway customerDatabaseGateway) {
        this.customerDatabaseGateway = customerDatabaseGateway;
    }

    @Override
    public CustomerOutput execute(CreateCustomerAccountInput createCustomerAccountInput) {
        this.customerDatabaseGateway.findCustomerByCpf(createCustomerAccountInput.getCpf())
                .ifPresent(customerAlreadyExists -> {
                    throw new IllegalArgumentException("Customer with CPF " + customerAlreadyExists.getCpf() + " already exists");
                });
        Customer customer = Customer.builder()
                .cpf(createCustomerAccountInput.getCpf())
                .name(createCustomerAccountInput.getName())
                .email(createCustomerAccountInput.getEmail())
                .build();
        Customer savedCustomer = this.customerDatabaseGateway.saveCustomer(customer);
        return CustomerOutput.mapFromEntity(savedCustomer);
    }
}
