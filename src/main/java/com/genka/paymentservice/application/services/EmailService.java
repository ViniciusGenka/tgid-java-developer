package com.genka.paymentservice.application.services;

import com.genka.paymentservice.application.services.inputs.SendEmailInput;

public interface EmailService {
    void execute(SendEmailInput sendEmailInput);
}
