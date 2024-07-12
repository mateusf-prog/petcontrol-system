package com.mateus.petcontrolsystem.services;

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
        emailToSend.setSubject("Código de verificação - PetControl System");
        emailToSend.setText(message);

        sendEmail(emailToSend);
    }

    public void sendWelcomeMessageToNewUser(String email, String name) {
        String message = "Olá " + name + "\n"+
                "\n" +
                "Seja bem-vindo ao PetControl System! Estamos muito felizes por você ter se juntado a nós.\n" +
                "\n" +
                "Esperamos que aproveite ao máximo nossos serviços. Em caso de dúvidas ou sugestões, não hesite em entrar em contato conosco.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe PetControl System";

        SimpleMailMessage emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject("Boas vindas ao PetControl System!");
        emailToSend.setText(message);

        sendEmail(emailToSend);
    }

    private void sendEmail(SimpleMailMessage data) {

        data.setFrom("noreply@petcontrolsystem");

        try {
            mailSender.send(data);
        } catch (MailSendException e) {
            throw new MailSendException("ERROR SENDING EMAIL CODE");
        } catch (MailAuthenticationException e) {
            throw new MailAuthenticationException("ERROR AUTHENTICATE MAIL SERVER");
        } catch (MailPreparationException e) {
            throw new MailPreparationException("ERROR DURING PREPARATION EMAIL MESSAGE");
        }
    }

    public void sendUpdateDataToEmail(String email, String name) {

        //todo implement
    }
}
