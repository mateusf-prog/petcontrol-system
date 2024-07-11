package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.common.EmailConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * This class is disable because the host smtp is free, therefore it have a monthly limit of requests.
 * NOTE: This affirmation its only this host configuration
 */

@Disabled
@SpringBootTest
public class EmailIntegrationTest {

    @Value("sandbox.smtp.mailtrap.io")
    private String host;
    @Value("2525")
    private Integer port;
    @Value("${SPRING_MAIL_USERNAME_TEST}")
    private String username;
    @Value("${SPRING_MAIL_PASSWORD_TEST}")
    private String password;

    public JavaMailSender setup() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

    @Test
    public void sendEmailTest_WithValidData_Returns200OK() {

        var validData = EmailConstants.getValidSimpleMailMessage();

        Assertions.assertDoesNotThrow(() -> setup().send(validData));
    }

    @Test
    public void sendEmailTest_WithInvalidSender_ThrowsMailException() {

        var withInvalidSender = EmailConstants.getSimpleMailMessageWithInvalidSender();

        Assertions.assertThrows(MailException.class, () -> setup().send(withInvalidSender));
    }

    @Test
    public void sendEmailTest_WithInvalidReceiver_ThrowsMailException() {

        var withInvalidReceiver = EmailConstants.getSimpleMailMessageWithInvalidReceiver();

        Assertions.assertThrows(MailException.class, () -> setup().send(withInvalidReceiver));
    }

    @Test
    public void sendEmailTest_WithInvalidText_ThrowsMailException() {

        var withInvalidText = EmailConstants.getSimpleMailMessageWithInvalidText();

        Assertions.assertThrows(MailException.class, () -> setup().send(withInvalidText));
    }

}
