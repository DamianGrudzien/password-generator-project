package com.passwordgenerator.damiangrudzien.model.response;

public class ErrorResponse {
    String error;

    public ErrorResponse(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
