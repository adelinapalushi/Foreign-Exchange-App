package com.forexapp.forexapplication.service;

import com.forexapp.forexapplication.dto.ConversionHistoryRequest;
import com.forexapp.forexapplication.dto.ConversionHistoryResponse;
import com.forexapp.forexapplication.dto.CurrencyConversionResponse;

import com.forexapp.forexapplication.entity.ExchangeRate;
import com.forexapp.forexapplication.exception.InternalCustomException;
import com.forexapp.forexapplication.repository.ExchangeRateRepository;
import com.forexapp.forexapplication.response.ErrorArgument;
import com.forexapp.forexapplication.response.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyConversionService {
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private ExchangeRateService exchangeRateService;


    public CurrencyConversionResponse convert(double amount, String sourceCurrency, String targetCurrency) {
            BigDecimal exchangeRate = exchangeRateService.getExchangeRate(sourceCurrency, targetCurrency);
            BigDecimal convertedAmount = BigDecimal.valueOf(amount).multiply(exchangeRate);

            return new CurrencyConversionResponse(convertedAmount.doubleValue());
    }

    public List<ConversionHistoryResponse> getConversionHistory(ConversionHistoryRequest request) {

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        Page<ExchangeRate> conversions;

        if (request.getTransactionId() != null) {
            conversions = exchangeRateRepository.findByTransactionId(request.getTransactionId(), pageRequest);
        } else if (request.getTransactionDate() != null) {
            conversions = exchangeRateRepository.findByTransactionDate(request.getTransactionDate(), pageRequest);
        } else {
            throw new IllegalArgumentException("Either transactionId or transactionDate must be provided.");
        }

        return conversions.stream()
                .map(conversion -> new ConversionHistoryResponse(
                        conversion.getTransactionId(),
                        conversion.getSourceCurrency(),
                        conversion.getTargetCurrency(),
                        conversion.getAmount(),
                        conversion.getConvertedAmount(),
                        conversion.getTransactionTimestamp()))
                .collect(Collectors.toList());
    }
}
