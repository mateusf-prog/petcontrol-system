package com.mateus.petcontrolsystem.models;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mateus.petcontrolsystem.services.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * This test only tests the method that actually sends the e-mail,
 * the others are irrelevant because they only write the e-mail data
 */
@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService service;

    @Mock
    private JavaMailSender mailSender;

    @Test
    public void sendEmail_ReturnsNothing() {

        service.sendEmail(any(SimpleMailMessage.class));
    }

    @Test
    public void sendEmail_ThrowsMailPreparationException() {

        doThrow(MailPreparationException.class).when(mailSender).send(any(SimpleMailMessage.class));

        assertThatThrownBy(() -> service.sendEmail(any(SimpleMailMessage.class))).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void sendEmail_ThrowsMailAuthenticationException() {

        doThrow(MailAuthenticationException.class).when(mailSender).send(any(SimpleMailMessage.class));

        assertThatThrownBy(() -> service.sendEmail(any(SimpleMailMessage.class))).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void sendEmail_ThrowsMailMailSendException() {

        doThrow(MailSendException.class).when(mailSender).send(any(SimpleMailMessage.class));

        assertThatThrownBy(() -> service.sendEmail(any(SimpleMailMessage.class))).isInstanceOf(RuntimeException.class);
    }
}
