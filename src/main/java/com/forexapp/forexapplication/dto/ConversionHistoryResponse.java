package com.forexapp.forexapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistoryResponse {
    private String transactionId;
    private String sourceCurrency;
    private String targetCurrency;
    private double amount;
    private double convertedAmount;
    private LocalDateTime transactionTimestamp;
}
