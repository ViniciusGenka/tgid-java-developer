package com.genka.paymentservice.application.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorDetails> internalServerErrorException(InternalServerErrorException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationErrorDetails> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError fieldError) {
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errors.add(new ValidationErrorDetails(field, message));
            }
        });
        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .errors(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> illegalArgumentException(IllegalArgumentException ex) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}