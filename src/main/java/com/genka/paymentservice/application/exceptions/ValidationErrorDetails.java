package com.genka.paymentservice.application.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationErrorDetails {
    private String field;
    private String message;
}
