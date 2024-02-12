package com.nhs.individual.Exception;

public class IllegalInputException extends APIException {

    public IllegalInputException(String message) {
        super(message);
    }

    public IllegalInputException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
