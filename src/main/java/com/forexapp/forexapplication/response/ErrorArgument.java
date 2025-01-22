package com.forexapp.forexapplication.response;

public class ErrorArgument {
    private String cod;
    private String value;

    public ErrorArgument(String cod, String value) {
        this.cod = cod;
        this.value = value;
    }

    public String getCod() {
        return cod;
    }

    public String getValue() {
        return value;
    }
}
