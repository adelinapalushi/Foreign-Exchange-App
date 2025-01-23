package com.forexapp.forexapplication;

import com.forexapp.forexapplication.dto.ExchangeRateRequest;
import com.forexapp.forexapplication.dto.ExchangeRateResponse;
import com.forexapp.forexapplication.service.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ExchangeRateServiceMockTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @Test
    public void testConvertCurrency() {
        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setBase("USD");
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        mockResponse.setRates(rates);

        when(restTemplate.getForObject("https://api.exchangerate-api.com/v4/latest/USD", ExchangeRateResponse.class, "USD"))
                .thenReturn(mockResponse);

        ExchangeRateRequest request = new ExchangeRateRequest();
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setAmount(100);

        double result = exchangeRateService.convertCurrency(request);
        assertEquals(96.0, result, 0.001);
    }
}