package com.willian.AlpacaFilmes.application.exceptions;

public class ApiResponseException extends RuntimeException {

    public ApiResponseException(String msg) {
        super(msg);
    }

    public ApiResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
