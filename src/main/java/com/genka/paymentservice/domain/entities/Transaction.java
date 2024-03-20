package com.genka.paymentservice.domain.entities;

import com.genka.paymentservice.domain.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"),
            @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    })
    private Wallet wallet;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;
    @Builder.Default
    private BigDecimal value = BigDecimal.ZERO;
}