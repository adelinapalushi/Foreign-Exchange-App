package com.forexapp.forexapplication.response;

public class ErrorCodes {
    public static final String ERRORS_CURRENCY_NULL= "Source or target currency cannot be null.";
    public static final String ERRORS_INVALID_API_RESPONSE= "Invalid API response for exchange rates.";
    public static final String ERRORS_RATE_NOT_FOUND= "Exchange rate for target currency not found.";
    public static final String ERRORS_CURRENCY_NOT_SUPPORTED= "Currency is not supported";
    public static final String ERRORS_GENERAL_ERROR= "Error general.";
    public static final String ERRORS_TRANSACTION_ID_NULL= "Transaction is null.";
    public static final String ERRORS_UNEXPECTED_RATE_TYPE= "Unexpected rate type.";
    public static final String ERRORS_INVALID_TARGET_CURRENCY= "Invalid target currency.";

}
