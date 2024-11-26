package com.snowmanlabs.challenge.rental.infrastructure.out.mail;

import com.snowmanlabs.challenge.rental.domain.email.RentalEmailNotification;
import com.snowmanlabs.challenge.rental.domain.email.model.RentalEmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalEmailSender implements RentalEmailNotification {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(final RentalEmailMessage message) {
        var mailMessage = new SimpleMailMessage();
        mailMessage.setTo(message.getTo());
        mailMessage.setFrom(message.getFrom());
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getMessage());

        javaMailSender.send(mailMessage);
    }

}
