package com.mateus.petcontrolsystem.models;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mateus.petcontrolsystem.common.EmailConstants;
import com.mateus.petcontrolsystem.services.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService service;

    @Mock
    private JavaMailSender mailSender;

    @Test
    public void sendCodeToEmail_WithValidData_ReturnsNothing() {

        var email = EmailConstants.getValidEmail();
        var code = EmailConstants.getCode();
        var emailToSend = EmailConstants.getValidSimpleMailMessage();

        doNothing().when(mailSender).send(emailToSend);
        service.sendCodeToEmail(code, email);

        verify(mailSender).send(emailToSend);
    }

    @Test
    public void sendCodeToEmail_GlobalMessageException_ThrowsMailSendException() {

        var email = EmailConstants.getValidEmail();
        var code = EmailConstants.getCode();
        var emailToSend = EmailConstants.getValidSimpleMailMessage();

        doThrow(MailSendException.class).when(mailSender).send(emailToSend);

        assertThatThrownBy(() -> service.sendCodeToEmail(code, email)).isInstanceOf(MailSendException.class);
    }

    @Test
    public void sendCodeToEmail_WithInvalidAuthenticateDataHost_ThrowsMailAuthenticationException() {

        var email = EmailConstants.getValidEmail();
        var code = EmailConstants.getCode();
        var emailToSend = EmailConstants.getValidSimpleMailMessage();

        doThrow(MailAuthenticationException.class).when(mailSender).send(emailToSend);

        assertThatThrownBy(() -> service.sendCodeToEmail(code, email)).isInstanceOf(MailAuthenticationException.class);
    }
    @Test
    public void sendCodeToEmail_WithErrorInMailPreparation_ThrowsMailPreparationException() {

        var email = EmailConstants.getValidEmail();
        var code = EmailConstants.getCode();
        var emailToSend = EmailConstants.getValidSimpleMailMessage();

        doThrow(MailPreparationException.class).when(mailSender).send(emailToSend);

        assertThatThrownBy(() -> service.sendCodeToEmail(code, email)).isInstanceOf(MailPreparationException.class);
    }
}
