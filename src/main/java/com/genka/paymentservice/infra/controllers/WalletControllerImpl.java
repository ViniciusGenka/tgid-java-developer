package com.genka.paymentservice.infra.controllers;

import com.genka.paymentservice.application.usecases.CreateCustomerWallet;
import com.genka.paymentservice.application.usecases.TopUpCustomerWallet;
import com.genka.paymentservice.application.usecases.inputs.CreateCustomerWalletInput;
import com.genka.paymentservice.application.usecases.inputs.TopUpCustomerWalletInput;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;
import com.genka.paymentservice.application.usecases.outputs.WalletOutput;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
public class WalletControllerImpl {

    private final CreateCustomerWallet createCustomerWalletUseCase;
    private final TopUpCustomerWallet topUpCustomerWalletUseCase;

    public WalletControllerImpl(CreateCustomerWallet createCustomerWalletUseCase, TopUpCustomerWallet topUpCustomerWalletUseCase) {
        this.createCustomerWalletUseCase = createCustomerWalletUseCase;
        this.topUpCustomerWalletUseCase = topUpCustomerWalletUseCase;
    }

    @PostMapping()
    public ResponseEntity<WalletOutput> createCustomerWallet(@RequestBody @Valid CreateCustomerWalletInput createCustomerWalletInput) {
        WalletOutput wallet = this.createCustomerWalletUseCase.execute(createCustomerWalletInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

    @PostMapping("/topup")
    public ResponseEntity<TransactionOutput> topUpCustomerWallet(@RequestBody @Valid TopUpCustomerWalletInput topUpCustomerWalletInput) {
        TransactionOutput transaction = this.topUpCustomerWalletUseCase.execute(topUpCustomerWalletInput);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }
}
