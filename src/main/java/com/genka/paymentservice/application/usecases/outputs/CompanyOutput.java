package com.genka.paymentservice.application.usecases.outputs;

import com.genka.paymentservice.domain.entities.Company;
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
public class CompanyOutput {
    private UUID id;
    private String cnpj;
    private BigDecimal balance;
    private String webhookUrl;

    public static CompanyOutput mapFromEntity(Company company) {
        return CompanyOutput.builder()
                .id(company.getId())
                .cnpj(company.getCnpj())
                .balance(company.getBalance())
                .webhookUrl(company.getWebhookUrl())
                .build();
    }
}
