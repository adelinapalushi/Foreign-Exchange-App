package com.forexapp.forexapplication.response;

import java.util.List;

public class ErrorResponse {
    private String cod;
    private List<ErrorArgument> args;

    public ErrorResponse(String cod, List<ErrorArgument> args) {
        this.cod = cod;
        this.args = args;
    }

    public String getCod() {
        return cod;
    }

    public List<ErrorArgument> getArgs() {
        return args;
    }
}
