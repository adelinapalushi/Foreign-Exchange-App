package com.forexapp.forexapplication.service;

import com.forexapp.forexapplication.exception.InternalCustomException;
import com.forexapp.forexapplication.repository.ExchangeRateRepository;
import com.forexapp.forexapplication.response.ErrorArgument;
import com.forexapp.forexapplication.response.ErrorCodes;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Cacheable("exchangeRates")
    public BigDecimal getExchangeRate(String source, String target) {
        if (source == null ) {
            throw new InternalCustomException(ErrorCodes.ERRORS_CURRENCY_NULL,
                    Collections.singletonList(new ErrorArgument("source", source))
            );
        }

        if (target == null ) {
            throw new InternalCustomException(ErrorCodes.ERRORS_CURRENCY_NULL,
                    Collections.singletonList(new ErrorArgument("target", target))
            );
        }

        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + source;

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = null;

        try {
            response = restTemplate.getForObject(apiUrl, Map.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND && e.getResponseBodyAsString().contains("unsupported_code")) {
                throw new InternalCustomException(ErrorCodes.ERRORS_CURRENCY_NOT_SUPPORTED,
                        Collections.singletonList(new ErrorArgument("source", source + " is not supported"))
                );
            }
            throw e;
        }

        if (response == null || !response.containsKey("rates")) {
            throw new InternalCustomException(ErrorCodes.ERRORS_INVALID_API_RESPONSE,
                    Collections.singletonList(new ErrorArgument("response", response != null ? response.toString() : "null"))
            );
        }

        // Fetch the list of supported currencies from the API response
        Map<String, Object> rates = (Map<String, Object>) response.get("rates");

        // Check if the source and target currencies are supported
        if (!rates.containsKey(source.toUpperCase())) {
            throw new InternalCustomException(ErrorCodes.ERRORS_CURRENCY_NOT_SUPPORTED,
                    Collections.singletonList(new ErrorArgument("source", source + " is not supported"))
            );
        }

        if (!rates.containsKey(target.toUpperCase())) {
            throw new InternalCustomException(ErrorCodes.ERRORS_CURRENCY_NOT_SUPPORTED,
                    Collections.singletonList(new ErrorArgument("target", target + " is not supported"))
            );
        }

        Object rateObject = rates.get(target);
        if (rateObject == null) {
            throw new InternalCustomException(ErrorCodes.ERRORS_RATE_NOT_FOUND,
                    Collections.singletonList(new ErrorArgument("rateObject", target + " rate not found"))
            );
        }

        // Convert rate to BigDecimal if it's a Double
        BigDecimal rate;
        if (rateObject instanceof Double) {
            rate = BigDecimal.valueOf((Double) rateObject);
        } else if (rateObject instanceof BigDecimal) {
            rate = (BigDecimal) rateObject;
        } else {
            throw new IllegalArgumentException("Unexpected rate type: " + rateObject.getClass());
        }

        return rate;
    }
}
