package com.genka.paymentservice.application.usecases.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyAccountInput {
    @NotBlank(message = "cnpj is required")
    private String cnpj;
    @NotBlank(message = "webhookUrl is required")
    private String webhookUrl;
}
