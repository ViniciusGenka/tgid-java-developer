package com.genka.paymentservice.application.usecases;

import com.genka.paymentservice.application.usecases.inputs.CreateCustomerAccountInput;
import com.genka.paymentservice.application.usecases.outputs.CustomerOutput;

public interface CreateCustomerAccount {
    CustomerOutput execute(CreateCustomerAccountInput createCustomerAccountInput);
}
