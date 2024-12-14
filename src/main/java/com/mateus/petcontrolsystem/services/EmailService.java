package com.mateus.petcontrolsystem.services;

import com.mateus.petcontrolsystem.services.utils.EmailMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendCodeToEmail(String code, String email) {

        var message = String.format("Seu código de verificação é %s\nSeu código tem duração de 10 minutos.", code);

        var emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject(EmailMessageConstants.SEND_CODE_SUBJECT);
        emailToSend.setText(message);

        sendEmail(emailToSend);
    }

    public void sendWelcomeMessageToNewUser(String email, String name) {

        var emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject(EmailMessageConstants.WELCOME_SUBJECT);
        emailToSend.setText(EmailMessageConstants.WELCOME_MESSAGE_BODY);

        sendEmail(emailToSend);
    }

    public void sendEmail(SimpleMailMessage data) {
        try {
            mailSender.send(data);
        } catch (MailSendException e) {
            logger.error("ERROR SENDING EMAIL CODE", e);
        } catch (MailAuthenticationException e) {
            logger.error("ERROR AUTHENTICATE MAIL SERVER", e);
        } catch (MailPreparationException e) {
            logger.error("ERROR DURING PREPARATION EMAIL MESSAGE", e);
        }
    }

    public void sendUpdateDataToEmail(String email, String name) {

        var emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject(EmailMessageConstants.UPDATE_DATA_SUBJECT);
        emailToSend.setText(EmailMessageConstants.UPDATE_DATA_BODY);

        sendEmail(emailToSend);
    }

    public void sendEmailNewPasswordSet(String email, String name) {

        var emailToSend = new SimpleMailMessage();
        emailToSend.setTo(email);
        emailToSend.setSubject(EmailMessageConstants.PASSWORD_RESET_SUBJECT);
        emailToSend.setText(EmailMessageConstants.PASSWORD_RESET_BODY);

        sendEmail(emailToSend);
    }
}
