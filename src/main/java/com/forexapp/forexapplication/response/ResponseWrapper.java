package com.forexapp.forexapplication.response;

import java.util.List;

public class ResponseWrapper<T> {
    private String status;
    private T payload;
    private ErrorResponse error;

    private ResponseWrapper(String status, T payload, ErrorResponse error) {
        this.status = status;
        this.payload = payload;
        this.error = error;
    }

    public static <T> ResponseWrapper<T> ok(T payload) {
        return new ResponseWrapper<>("SUCCESS", payload, null);
    }

    public static ResponseWrapper<Payload> error(String errorCode, List<ErrorArgument> args) {
        Payload payload = new Payload("REJECTED");
        return new ResponseWrapper<>("ERROR", payload, new ErrorResponse(errorCode, args));
    }

    public String getStatus() {
        return status;
    }

    public T getPayload() {
        return payload;
    }

    public ErrorResponse getError() {
        return error;
    }
}
