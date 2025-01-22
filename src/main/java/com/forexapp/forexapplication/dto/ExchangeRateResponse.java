package com.forexapp.forexapplication.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateResponse {
    private String base;
    private String date;
    private Map<String, Double> rates;
}
