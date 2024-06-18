package com.nhs.individual.exception.handler;

import com.nhs.individual.exception.DuplicateElementException;
import com.nhs.individual.exception.InvalidTokenException;
import com.nhs.individual.exception.ResourceNotFoundException;
import com.nhs.individual.responsemessage.ResponseMessage;
import com.nhs.individual.secure.JwtProvider;
import com.nhs.individual.utils.RequestUtils;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;

@RestControllerAdvice
public class APIExceptionHandler {
    @Autowired
    RequestUtils requestUtils;
    @Autowired
    JwtProvider jwtProvider;
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMessage handleNoResourceFoundException(NoResourceFoundException e) {
        e.printStackTrace();
        return ResponseMessage
               .builder()
               .message(e.getLocalizedMessage())
               .details(e.getClass().getName())
               .error()
               .ok();
    }
    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseMessage handlerException(AccessDeniedException e){
        return ResponseMessage
               .builder()
               .message(e.getLocalizedMessage())
               .details(e.getClass().getName())
               .error()
               .ok();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseMessage handleAccessDeniedException(AccessDeniedException e) {
        return ResponseMessage
               .builder()
               .message(e.getLocalizedMessage())
               .details(e.getClass().getName())
               .error()
               .ok();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage validateException(ConstraintViolationException e) {
        return ResponseMessage
                .builder()
                .message(e.getLocalizedMessage())
                .details(e.getClass().getName())
                .error()
                .ok();
    }
    @ExceptionHandler(NonUniqueObjectException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseMessage handleNonUniqueObjectException(NonUniqueObjectException e) {
        return ResponseMessage
                .builder()
                .message(e.getLocalizedMessage())
                .details(e.getClass().getName())
                .error()
                .ok();
    }
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage sqlExceptionHandler(SQLException e) {
        return ResponseMessage
                .builder()
                .message(e.getMessage())
                .details(e.getClass().getName())
                .statusCode(e.getErrorCode())
                .error()
                .ok();
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMessage handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseMessage
                .builder()
                .message(e.getMessage())
                .error()
                .ok();
    }

    @ExceptionHandler(DuplicateElementException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseMessage handleNonUniqueObjectException(DuplicateElementException e) {
        return ResponseMessage
                .builder()
                .message(e.getMessage())
                .error()
                .ok();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handleSqlException(IllegalArgumentException e) {
        return ResponseMessage
                .builder()
                .message(e.getMessage())
                .error()
                .ok();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> handleAuthenticationException(BadCredentialsException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseMessage
                        .builder()
                        .message(e.getMessage())
                        .error()
                        .ok());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResponseMessage> handleInvalidTokenException(InvalidTokenException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(ResponseMessage
                        .builder()
                        .message(e.getMessage())
                        .error()
                        .ok());
    }



}
