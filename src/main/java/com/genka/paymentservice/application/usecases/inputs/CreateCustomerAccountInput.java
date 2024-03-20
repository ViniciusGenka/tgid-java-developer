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
public class CreateCustomerAccountInput {
    @NotBlank(message = "cpf is required")
    private String cpf;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
}
