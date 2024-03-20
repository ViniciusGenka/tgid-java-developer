package com.genka.paymentservice.application.services.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailInput {
    String from;
    String to;
    String subject;
    String text;
}
