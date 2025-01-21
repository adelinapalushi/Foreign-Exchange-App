package com.forexapp.forexapplication.response;

public class Payload {
    private String status;

    public Payload(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
