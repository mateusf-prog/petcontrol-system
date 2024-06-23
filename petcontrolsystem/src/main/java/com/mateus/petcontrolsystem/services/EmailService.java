package com.mateus.petcontrolsystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
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

        mailSender.send(emailToSend);
    }
}
