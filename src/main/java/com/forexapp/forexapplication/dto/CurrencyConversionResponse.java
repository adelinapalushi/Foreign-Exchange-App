package com.forexapp.forexapplication.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CurrencyConversionResponse {
    private double convertedAmount;
    private String transactionId;

    public CurrencyConversionResponse(double convertedAmount) {
        this.convertedAmount = convertedAmount;
        this.transactionId = UUID.randomUUID().toString();
    }
}
