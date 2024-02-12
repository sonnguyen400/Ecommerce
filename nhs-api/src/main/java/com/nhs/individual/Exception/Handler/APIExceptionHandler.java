package com.nhs.individual.Exception.Handler;

import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.ResponseMessage.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler({Exception.class,SQLException.class})
    public ResponseEntity<String> handleException(Exception e){
        e.printStackTrace();
        return ResponseEntity
             .status(HttpStatus.NOT_ACCEPTABLE)
             .body(e.getMessage());
    }
    @ExceptionHandler({ResourceNotFoundException.class,IllegalArgumentException.class})
    public ResponseEntity<ResponseMessage> handleSqlException(SQLException e){
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseMessage
                        .builder()
                        .message(e.getMessage())
                        .error()
                        .ok());
    }
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ResponseMessage> handleAuthenticationException(BadCredentialsException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseMessage
                        .builder()
                        .message(e.getMessage())
                        .error()
                        .ok());
    }
}
