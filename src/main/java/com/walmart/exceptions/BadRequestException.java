package com.walmart.exceptions;

import org.springframework.validation.Errors;

public class BadRequestException extends RuntimeException {
    private Errors errors;

    public BadRequestException(Errors errors) {
        super("Bad request");
        this.errors = errors;
    }

    public Errors getErrors() {
        return this.errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}

