package com.genka.paymentservice.application.usecases;

import com.genka.paymentservice.application.usecases.inputs.CreateCompanyAccountInput;
import com.genka.paymentservice.application.usecases.outputs.CompanyOutput;

public interface CreateCompanyAccount {
    CompanyOutput execute(CreateCompanyAccountInput createCompanyAccountInput);
}
