package com.forexapp.forexapplication;

import com.forexapp.forexapplication.dto.ConversionHistoryRequest;
import com.forexapp.forexapplication.dto.ConversionHistoryResponse;
import com.forexapp.forexapplication.dto.CurrencyConversionResponse;
import com.forexapp.forexapplication.entity.ExchangeRate;
import com.forexapp.forexapplication.repository.ExchangeRateRepository;
import com.forexapp.forexapplication.service.CurrencyConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CurrencyConversionServiceTest {
    @Autowired
    private  CurrencyConversionService service = new CurrencyConversionService();


    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Test
    void testConvertCurrency() {
        CurrencyConversionResponse response = service.convert(100, "USD", "EUR");
        assertNotNull(response.getTransactionId());
        assertEquals(96.1, response.getConvertedAmount(), 0.01);
    }

}
