package com.forexapp.forexapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_currency", nullable = false)
    private String sourceCurrency;

    @Column(name = "target_currency",nullable = false)
    private String targetCurrency;

    @Column(name = "rate",nullable = false)
    private double rate;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;


    @Column(nullable = false)
    private double amount;

    @Column(name = "converted_amount", nullable = false)
    private double convertedAmount;

    @Column(name = "transaction_timestamp", nullable = false)
    private LocalDateTime transactionTimestamp;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    public ExchangeRate(String txn123,
                        String usd,
                        String eur,
                        double v,
                        double v1,
                        LocalDateTime now) {
    }
}
