package com.genka.paymentservice.application.usecases.outputs;

import com.genka.paymentservice.domain.entities.Transaction;
import com.genka.paymentservice.domain.entities.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionOutput {
    private UUID id;
    private WalletOutput wallet;
    private TransactionType type;
    private BigDecimal value;

    public static TransactionOutput mapFromEntity(Transaction transaction) {
        return TransactionOutput.builder()
                .id(transaction.getId())
                .wallet(WalletOutput.mapFromEntity(transaction.getWallet()))
                .type(transaction.getType())
                .value(transaction.getValue())
                .build();
    }
}