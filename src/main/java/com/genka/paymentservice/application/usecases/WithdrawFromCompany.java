package com.genka.paymentservice.application.usecases;

import com.genka.paymentservice.application.usecases.inputs.WithdrawFromCompanyInput;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;

public interface WithdrawFromCompany {
    TransactionOutput execute(WithdrawFromCompanyInput withdrawFromCompanyInput);
}
