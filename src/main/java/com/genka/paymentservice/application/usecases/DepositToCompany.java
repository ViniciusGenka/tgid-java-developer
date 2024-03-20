package com.genka.paymentservice.application.usecases;

import com.genka.paymentservice.application.usecases.inputs.DepositToCompanyInput;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;

public interface DepositToCompany {
    TransactionOutput execute(DepositToCompanyInput depositToCompanyInput);
}
