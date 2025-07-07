package com.microservice.commons.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Projeto1Exception {
    public NotFoundException(HttpStatus status, String message, Object... args) {
        super(HttpStatus.NOT_FOUND, message, args);
    }
}
