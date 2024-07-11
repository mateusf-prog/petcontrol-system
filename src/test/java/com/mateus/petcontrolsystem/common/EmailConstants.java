package com.mateus.petcontrolsystem.common;

import org.springframework.mail.SimpleMailMessage;

public class EmailConstants {

    public static String getValidEmail() {
        return "valid-email@test.com";
    }

    public static String getCode() {
        return "12345";
    }

    public static SimpleMailMessage getValidSimpleMailMessage() {
        String message = "Seu código de verificação é " + getCode() + "\nSeu código tem duração de 10 minutos.";

        SimpleMailMessage emailToSend = new SimpleMailMessage();
        emailToSend.setTo(getValidEmail());
        emailToSend.setFrom("noreply@petcontrolsystem");
        emailToSend.setSubject("Código de verificação - PetControl System");
        emailToSend.setText(message);
        return  emailToSend;
    }

    public static SimpleMailMessage getSimpleMailMessageWithInvalidSender() {
        String message = "Seu código de verificação é " + getCode() + "\nSeu código tem duração de 10 minutos.";

        SimpleMailMessage emailToSend = new SimpleMailMessage();
        emailToSend.setTo(getValidEmail());
        emailToSend.setFrom("invalid sender");
        emailToSend.setSubject("Código de verificação - PetControl System");
        emailToSend.setText(message);
        return  emailToSend;
    }

    public static SimpleMailMessage getSimpleMailMessageWithInvalidReceiver() {
        String message = "Seu código de verificação é " + getCode() + "\nSeu código tem duração de 10 minutos.";

        SimpleMailMessage emailToSend = new SimpleMailMessage();
        emailToSend.setTo("invalid receiver");
        emailToSend.setFrom("noreply@petcontrolsystem");
        emailToSend.setSubject("Código de verificação - PetControl System");
        emailToSend.setText(message);
        return  emailToSend;
    }

    public static SimpleMailMessage getSimpleMailMessageWithInvalidText() {
        String message = null;

        SimpleMailMessage emailToSend = new SimpleMailMessage();
        emailToSend.setTo(getValidEmail());
        emailToSend.setFrom("noreply@petcontrolsystem");
        emailToSend.setSubject("Código de verificação - PetControl System");
        emailToSend.setText(message);
        return  emailToSend;
    }
}
