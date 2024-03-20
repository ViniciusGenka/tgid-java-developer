package com.genka.paymentservice.application.usecases.outputs;

import com.genka.paymentservice.domain.entities.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletOutput {
    private BigDecimal balance;
    private CustomerOutput customer;
    private CompanyOutput company;

    public static WalletOutput mapFromEntity(Wallet wallet) {
        return WalletOutput.builder()
                .balance(wallet.getBalance())
                .customer(CustomerOutput.mapFromEntity(wallet.getCustomer()))
                .company(CompanyOutput.mapFromEntity(wallet.getCompany()))
                .build();
    }
}
