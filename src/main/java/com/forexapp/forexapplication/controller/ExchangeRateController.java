package com.forexapp.forexapplication.controller;

import com.forexapp.forexapplication.exception.InternalCustomException;
import com.forexapp.forexapplication.response.ErrorCodes;
import com.forexapp.forexapplication.response.ResponseWrapper;
import com.forexapp.forexapplication.service.ExchangeRateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ExchangeRateController {
    protected static final Logger logger = LogManager.getLogger(ExchangeRateController.class);
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/api/exchange-rate")
    public ResponseEntity<?> getExchangeRate(@RequestParam String from, @RequestParam String to) {
        try {
            BigDecimal exchangeRate = exchangeRateService.getExchangeRate(from, to);
            return ResponseEntity.ok(ResponseWrapper.ok(exchangeRate));
        } catch (InternalCustomException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity.ok(ResponseWrapper.error(e.getErrorCode(), e.getErrorArguments()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrapper.error(ErrorCodes.ERRORS_GENERAL_ERROR, null));
        }
    }
}


