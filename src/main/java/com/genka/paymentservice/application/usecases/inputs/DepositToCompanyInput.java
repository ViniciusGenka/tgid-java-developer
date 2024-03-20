package com.genka.paymentservice.application.usecases.inputs;

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
public class DepositToCompanyInput {
    @NotNull(message = "customerId is required")
    private UUID customerId;
    @NotNull(message = "companyId is required")
    private UUID companyId;
    @NotNull(message = "value is required")
    private BigDecimal value;
}
