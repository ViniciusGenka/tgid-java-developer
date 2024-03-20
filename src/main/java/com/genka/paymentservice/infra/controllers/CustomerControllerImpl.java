package com.genka.paymentservice.infra.controllers;

import com.genka.paymentservice.application.usecases.CreateCustomerAccount;
import com.genka.paymentservice.application.usecases.inputs.CreateCustomerAccountInput;
import com.genka.paymentservice.application.usecases.outputs.CustomerOutput;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl {

    private final CreateCustomerAccount createCustomerAccountUseCase;


    public CustomerControllerImpl(CreateCustomerAccount createCustomerAccountUseCase) {
        this.createCustomerAccountUseCase = createCustomerAccountUseCase;
    }

    @PostMapping()
    public ResponseEntity<CustomerOutput> createCustomerAccount(@RequestBody @Valid CreateCustomerAccountInput createCustomerAccountInput) {
        CustomerOutput customer = this.createCustomerAccountUseCase.execute(createCustomerAccountInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
