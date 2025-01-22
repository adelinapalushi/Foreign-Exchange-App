package com.forexapp.forexapplication.dto;

import lombok.Data;

@Data
public class ExchangeRateRequest {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
}
