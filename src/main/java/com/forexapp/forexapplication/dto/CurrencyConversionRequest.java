package com.forexapp.forexapplication.dto;

import lombok.Data;

@Data
public class CurrencyConversionRequest {
    private double amount;
    private String sourceCurrency;
    private String targetCurrency;
}
