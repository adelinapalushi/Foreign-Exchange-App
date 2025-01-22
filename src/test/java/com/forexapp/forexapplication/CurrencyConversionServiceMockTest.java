package com.forexapp.forexapplication;

import com.forexapp.forexapplication.dto.ConversionHistoryRequest;
import com.forexapp.forexapplication.dto.ConversionHistoryResponse;
import com.forexapp.forexapplication.entity.ExchangeRate;
import com.forexapp.forexapplication.repository.ExchangeRateRepository;
import com.forexapp.forexapplication.service.CurrencyConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CurrencyConversionServiceMockTest {
    @InjectMocks
    private CurrencyConversionService service;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetConversionHistoryByTransactionId() {
        String transactionId = "txn123";
        ConversionHistoryRequest request = new ConversionHistoryRequest();
        request.setTransactionId(transactionId);
        request.setPage(0);
        request.setSize(10);

        ExchangeRate exchangeRate1 = new ExchangeRate("txn123", "USD", "EUR", 100.0, 96.1, LocalDateTime.now());
        ExchangeRate exchangeRate2 = new ExchangeRate("txn124", "USD", "EUR", 200.0, 192.2, LocalDateTime.now());

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        when(exchangeRateRepository.findByTransactionId(transactionId, pageRequest))
                .thenReturn(Page.empty());

        List<ConversionHistoryResponse> history = service.getConversionHistory(request);

        assertNotNull(history);
        assertTrue(history.isEmpty(), "The conversion history list should be empty.");

        verify(exchangeRateRepository, times(1)).findByTransactionId(transactionId, pageRequest);
    }

    @Test
    void testGetConversionHistoryByTransactionDate() {
        // Arrange
        LocalDate transactionDate = LocalDate.now();
        ConversionHistoryRequest request = new ConversionHistoryRequest();
        request.setTransactionDate(transactionDate);
        request.setPage(0);
        request.setSize(10);

        ExchangeRate exchangeRate1 = new ExchangeRate("txn123", "USD", "EUR", 100.0, 96.1, LocalDateTime.now());
        ExchangeRate exchangeRate2 = new ExchangeRate("txn124", "USD", "EUR", 200.0, 192.2, LocalDateTime.now());

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        when(exchangeRateRepository.findByTransactionDate(transactionDate, pageRequest))
                .thenReturn(Page.empty());

        List<ConversionHistoryResponse> history = service.getConversionHistory(request);

        assertNotNull(history);
        assertTrue(history.isEmpty(), "The conversion history list should be empty.");

        verify(exchangeRateRepository, times(1)).findByTransactionDate(transactionDate, pageRequest);
    }

    @Test
    void testGetConversionHistoryWithInvalidRequest() {
        ConversionHistoryRequest request = new ConversionHistoryRequest();
        request.setPage(0);
        request.setSize(10);

        assertThrows(IllegalArgumentException.class, () -> {
            service.getConversionHistory(request);
        });
    }
}
