package com.genka.paymentservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    @EmbeddedId
    private WalletId id;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("customerId")
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("companyId")
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    public void topUp(BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid transaction value");
        }
        this.setBalance(this.getBalance().add(value));
    }
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