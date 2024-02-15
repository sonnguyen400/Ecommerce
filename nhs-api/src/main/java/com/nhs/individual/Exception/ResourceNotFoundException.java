package com.nhs.individual.Exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ResponseException{
    protected HttpStatus httpStatus=HttpStatus.NOT_FOUND;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
