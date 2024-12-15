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

        var message = String.format("Seu código de verificação é %s\nSeu código tem duração de 10 minutos.", code);

        var emailToSend = new SimpleMailMessage();
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

        var emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject("Boas vindas - PetControl System!");
        emailToSend.setText(message);

        sendEmail(emailToSend);
    }

    public void sendEmail(SimpleMailMessage data) {

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

        var message = "Olá " + name + ",\n"+
                "\n" +
                "Gostaríamos de informar que os seus dados foram atualizados com sucesso em nosso sistema.\n" +
                "\n" +
                "Se você não solicitou essa atualização ou se notar alguma discrepância nas informações, por favor, " +
                "entre em contato conosco imediatamente para que possamos corrigir qualquer problema.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe PetControl System";

        var emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject("Boas vindas ao PetControl System!");
        emailToSend.setText(message);

        sendEmail(emailToSend);
    }

    public void sendEmailNewPasswordSet(String email, String name) {

        var message = "Olá " + name + ",\n"+
                "\n" +
                "Gostaríamos de informar que sua senha foi atualizada com sucesso em nosso sistema.\n" +
                "\n" +
                "Se você não solicitou essa atualização ou se notar alguma discrepância nas informações, por favor, " +
                "entre em contato conosco imediatamente para que possamos corrigir qualquer problema.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe PetControl System";

        var emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject("Boas vindas ao PetControl System!");
        emailToSend.setText(message);

        sendEmail(emailToSend);
    }
}
