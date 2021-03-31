package com.library.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not add")
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}