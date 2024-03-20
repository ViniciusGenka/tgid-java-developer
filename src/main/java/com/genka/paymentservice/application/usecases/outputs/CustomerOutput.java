package com.genka.paymentservice.application.usecases.outputs;

import com.genka.paymentservice.domain.entities.Customer;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOutput {
    private UUID id;
    private String name;
    private String email;
    @Column(unique = true)
    private String cpf;

    public static CustomerOutput mapFromEntity(Customer customer) {
        return CustomerOutput.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .cpf(customer.getCpf())
                .build();
    }
}
