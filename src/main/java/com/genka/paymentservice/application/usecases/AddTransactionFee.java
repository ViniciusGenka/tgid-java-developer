package com.genka.paymentservice.application.usecases;

import com.genka.paymentservice.application.usecases.inputs.AddTransactionFeeInput;
import com.genka.paymentservice.application.usecases.outputs.FeeOutput;

public interface AddTransactionFee {
    FeeOutput execute(AddTransactionFeeInput addTransactionFeeInput);
}
