package com.genka.paymentservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String cnpj;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
    @Column(nullable = false)
    private String webhookUrl;

    public void deposit(BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid transaction value");
        }
        this.setBalance(this.getBalance().add(value));
    }

    public void withdraw(BigDecimal value) {
        if(this.getBalance().compareTo(value) < 0 || this.getBalance().equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if(value.equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Invalid transaction value");
        }
        this.setBalance(this.getBalance().subtract(value));
    }
}