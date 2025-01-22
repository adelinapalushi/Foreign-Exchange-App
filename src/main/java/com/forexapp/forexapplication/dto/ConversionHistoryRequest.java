package com.forexapp.forexapplication.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ConversionHistoryRequest {
    private String transactionId;
    private LocalDate transactionDate;
    private int page;
    private int size;
}
