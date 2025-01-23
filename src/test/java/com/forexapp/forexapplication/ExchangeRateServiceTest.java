package com.forexapp.forexapplication;

import com.forexapp.forexapplication.exception.InternalCustomException;
import com.forexapp.forexapplication.service.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExchangeRateServiceTest {
    @Autowired
    private ExchangeRateService exchangeRateService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetExchangeRate_ValidCurrencies_ReturnsRate() {
        String source = "USD";
        String target = "EUR";
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("rates", Map.of(target, 0.85));

        when(restTemplate.getForObject("https://api.exchangerate-api.com/v4/latest/" + source, Map.class))
                .thenReturn(mockResponse);

        BigDecimal exchangeRate = exchangeRateService.getExchangeRate(source, target);
        assertEquals(new BigDecimal("0.96"), exchangeRate);
    }

    @Test
    public void testGetExchangeRate_NullSource_ThrowsException() {
        assertThrows(InternalCustomException.class, () -> exchangeRateService.getExchangeRate(null, "EUR"));
    }

    @Test
    public void testGetExchangeRate_NullTarget_ThrowsException() {
        assertThrows(InternalCustomException.class, () -> exchangeRateService.getExchangeRate("USD", null));
    }

}
