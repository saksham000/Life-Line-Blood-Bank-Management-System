package com.lifeline.lifelinebloodbank.LifeLineProject.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException; // New Import
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // 1. Handle General Exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 2. Handle Validation Errors (@NotNull, etc.)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex, 
            @NonNull HttpHeaders headers, 
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {
        
        String errorMessage = "Validation Failed";
        FieldError fieldError = ex.getBindingResult().getFieldError();
        
        if (fieldError != null) {
            errorMessage = "Validation Error: " + fieldError.getDefaultMessage();
        }

        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            errorMessage,
            request.getDescription(false)
        );
        
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // 3. Handle Unsupported Media Type (The 415 Error)
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            @NonNull HttpMediaTypeNotSupportedException ex, 
            @NonNull HttpHeaders headers, 
            @NonNull HttpStatusCode status, 
            @NonNull WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            "Invalid Content-Type. Please use 'application/json'", 
            ex.getMessage() // This will show "Content-Type 'text/plain' is not supported"
        );
        
        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}