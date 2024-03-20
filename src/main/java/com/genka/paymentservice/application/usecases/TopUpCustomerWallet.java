package com.genka.paymentservice.application.usecases;

import com.genka.paymentservice.application.usecases.inputs.TopUpCustomerWalletInput;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;

public interface TopUpCustomerWallet {
    TransactionOutput execute(TopUpCustomerWalletInput topUpCustomerWalletInput);
}
