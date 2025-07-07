package com.microservice.commons.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends Projeto1Exception {
    public InternalServerErrorException(HttpStatus status, String message, Object... args) {
        super(status, message, args);
    }

    public InternalServerErrorException(Throwable cause, HttpStatus status, String message, Object... args) {
        super(cause, status, message, args);
    }
}
