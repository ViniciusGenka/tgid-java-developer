package com.genka.paymentservice.infra.services;

import com.genka.paymentservice.application.services.EmailService;
import com.genka.paymentservice.application.services.inputs.SendEmailInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void execute(SendEmailInput sendEmailInput) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendEmailInput.getFrom());
        message.setTo(sendEmailInput.getTo());
        message.setText(sendEmailInput.getText());
        message.setSubject(sendEmailInput.getSubject());
        mailSender.send(message);
    }
}
