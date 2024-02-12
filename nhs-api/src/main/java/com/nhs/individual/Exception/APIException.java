package com.nhs.individual.Exception;

public class APIException extends RuntimeException{
    private String message;
    private Integer errorCode;
    public  APIException(String message){
        super(message);
    }
    public  APIException(String message,Throwable throwable){
        super(message,throwable);
    }
}
