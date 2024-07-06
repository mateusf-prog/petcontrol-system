package com.mateus.petcontrolsystem.services;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendCodeToEmail(String code, String email) {

        String message = "Seu código de verificação é " + code + "\nSeu código tem duração de 10 minutos.";

        SimpleMailMessage emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setFrom("noreply@petcontrolsystem");
        emailToSend.setSubject("Código de verificação - PetControl System");
        emailToSend.setText(message);

        try {
            mailSender.send(emailToSend);
        } catch (MailSendException e) {
            throw new MailSendException("ERROR SENDING EMAIL CODE");
        } catch (MailAuthenticationException e) {
            throw new MailAuthenticationException("ERROR AUTHENTICATE MAIL SERVER");
        } catch (MailPreparationException e) {
            throw new MailPreparationException("ERROR DURING PREPARATION EMAIL MESSAGE");
        }
    }
}
