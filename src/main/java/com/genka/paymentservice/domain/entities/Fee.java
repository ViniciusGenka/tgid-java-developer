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
@Table(name = "fees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Builder.Default
    private BigDecimal value = BigDecimal.ZERO;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;
}