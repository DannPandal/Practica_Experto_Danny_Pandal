package com.devp.practice2Semana9.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomErrorResponse {
    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    public CustomErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
