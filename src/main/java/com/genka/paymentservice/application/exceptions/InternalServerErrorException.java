package com.genka.paymentservice.application.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String msg) {
        super(msg);
    }
}