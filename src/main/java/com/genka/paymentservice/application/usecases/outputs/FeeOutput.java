package com.genka.paymentservice.application.usecases.outputs;

import com.genka.paymentservice.domain.entities.Company;
import com.genka.paymentservice.domain.entities.Fee;
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
public class FeeOutput {
    private UUID id;
    private UUID companyId;
    private String name;
    private BigDecimal value;
    private TransactionType transactionType;

    public static FeeOutput mapFromEntity(Fee fee) {
        return FeeOutput.builder()
                .id(fee.getId())
                .companyId(fee.getCompany().getId())
                .name(fee.getName())
                .value(fee.getValue())
                .transactionType(fee.getTransactionType())
                .build();
    }
}
