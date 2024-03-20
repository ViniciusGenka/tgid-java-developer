package com.genka.paymentservice.application.usecases.inputs;

import com.genka.paymentservice.domain.entities.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddTransactionFeeInput {
    @NotNull(message = "companyId is required")
    private UUID companyId;
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "value is required")
    private BigDecimal value;
    @NotNull(message = "transactionType is required")
    private TransactionType transactionType;
}
