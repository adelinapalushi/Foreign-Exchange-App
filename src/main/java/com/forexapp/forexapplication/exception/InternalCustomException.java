package com.forexapp.forexapplication.exception;

import com.forexapp.forexapplication.response.ErrorArgument;

import java.util.List;

public class InternalCustomException extends RuntimeException {
    private final String errorCode;
    private final List<ErrorArgument> errorArguments;

    public InternalCustomException(String errorCode, List<ErrorArgument> errorArguments) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorArguments = errorArguments;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public List<ErrorArgument> getErrorArguments() {
        return errorArguments;
    }
}
