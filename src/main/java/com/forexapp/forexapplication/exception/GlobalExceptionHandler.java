package com.forexapp.forexapplication.exception;

import com.forexapp.forexapplication.response.ErrorArgument;
import com.forexapp.forexapplication.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InternalCustomException.class)
    public ResponseEntity<ResponseWrapper<?>> handleInternalException(InternalCustomException ex, WebRequest request) {
        List<ErrorArgument> args = new ArrayList<>();
        args.add(new ErrorArgument(ex.getErrorCode(), ex.getErrorArguments().toString()));

        return new ResponseEntity<>(ResponseWrapper.error(ex.getErrorCode(), args), HttpStatus.OK);
    }

}
