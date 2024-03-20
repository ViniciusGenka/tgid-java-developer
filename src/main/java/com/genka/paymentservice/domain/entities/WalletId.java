package com.genka.paymentservice.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletId implements Serializable {
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "company_id")
    private UUID companyId;
}
