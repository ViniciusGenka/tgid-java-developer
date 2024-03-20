package com.genka.paymentservice.infra.controllers;

import com.genka.paymentservice.application.usecases.AddTransactionFee;
import com.genka.paymentservice.application.usecases.CreateCompanyAccount;
import com.genka.paymentservice.application.usecases.DepositToCompany;
import com.genka.paymentservice.application.usecases.WithdrawFromCompany;
import com.genka.paymentservice.application.usecases.inputs.AddTransactionFeeInput;
import com.genka.paymentservice.application.usecases.inputs.CreateCompanyAccountInput;
import com.genka.paymentservice.application.usecases.inputs.DepositToCompanyInput;
import com.genka.paymentservice.application.usecases.inputs.WithdrawFromCompanyInput;
import com.genka.paymentservice.application.usecases.outputs.CompanyOutput;
import com.genka.paymentservice.application.usecases.outputs.FeeOutput;
import com.genka.paymentservice.application.usecases.outputs.TransactionOutput;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyControllerImpl {

    private final CreateCompanyAccount createCompanyAccountUseCase;
    private final DepositToCompany depositToCompanyUseCase;
    private final WithdrawFromCompany withdrawFromCompanyUseCase;
    private final AddTransactionFee addTransactionFeeUseCase;

    public CompanyControllerImpl(CreateCompanyAccount createCompanyAccountUseCase, DepositToCompany depositToCompanyUseCase, WithdrawFromCompany withdrawFromCompanyUseCase, AddTransactionFee addTransactionFeeUseCase) {
        this.createCompanyAccountUseCase = createCompanyAccountUseCase;
        this.depositToCompanyUseCase = depositToCompanyUseCase;
        this.withdrawFromCompanyUseCase = withdrawFromCompanyUseCase;
        this.addTransactionFeeUseCase = addTransactionFeeUseCase;
    }

    @PostMapping()
    public ResponseEntity<CompanyOutput> createCompanyAccount(@RequestBody @Valid CreateCompanyAccountInput createCompanyAccountInput) {
        CompanyOutput company = this.createCompanyAccountUseCase.execute(createCompanyAccountInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @PostMapping("/deposits")
    public ResponseEntity<TransactionOutput> depositToCompany(@RequestBody @Valid DepositToCompanyInput depositToCompanyInput) {
        TransactionOutput transaction = this.depositToCompanyUseCase.execute(depositToCompanyInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @PostMapping("/withdraws")
    public ResponseEntity<TransactionOutput> withdrawFromCompany(@RequestBody @Valid WithdrawFromCompanyInput withdrawFromCompanyInput) {
        TransactionOutput transaction = this.withdrawFromCompanyUseCase.execute(withdrawFromCompanyInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @PostMapping("/fees")
    public ResponseEntity<FeeOutput> addTransactionFee(@RequestBody @Valid AddTransactionFeeInput addTransactionFeeInput) {
        FeeOutput fee = this.addTransactionFeeUseCase.execute(addTransactionFeeInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(fee);
    }
}
