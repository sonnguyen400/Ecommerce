package com.nhs.individual.Exception;

public class DuplicateElementException extends ResponseException{
    public DuplicateElementException(String message) {
        super(message);
    }

    public DuplicateElementException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DuplicateElementException(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public DuplicateElementException(String message, Throwable throwable, Integer errorCode) {
        super(message, throwable, errorCode);
    }
}
