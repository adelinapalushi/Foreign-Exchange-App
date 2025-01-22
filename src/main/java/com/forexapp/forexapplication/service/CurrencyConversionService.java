package com.forexapp.forexapplication.service;

import com.forexapp.forexapplication.dto.CurrencyConversionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyConversionService {
    @Autowired
    private  ExchangeRateService exchangeRateService;


    public CurrencyConversionResponse convert(double amount, String sourceCurrency, String targetCurrency) {
            BigDecimal exchangeRate = exchangeRateService.getExchangeRate(sourceCurrency, targetCurrency);
            BigDecimal convertedAmount = BigDecimal.valueOf(amount).multiply(exchangeRate);

            return new CurrencyConversionResponse(convertedAmount.doubleValue());
    }
}
