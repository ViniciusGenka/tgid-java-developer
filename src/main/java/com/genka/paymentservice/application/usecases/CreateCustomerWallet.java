package com.genka.paymentservice.application.usecases;

import com.genka.paymentservice.application.usecases.inputs.CreateCustomerWalletInput;
import com.genka.paymentservice.application.usecases.outputs.WalletOutput;

public interface CreateCustomerWallet {
    WalletOutput execute(CreateCustomerWalletInput createCustomerWalletInput);
}
