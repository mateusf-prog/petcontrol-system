package com.mateus.petcontrolsystem.infra.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class EmailTestConfig {

    @Value("sandbox.smtp.mailtrap.io")
    private String host;
    @Value("2525")
    private Integer port;
    @Value("${SPRING_MAIL_USERNAME_TEST}")
    private String username;
    @Value("${SPRING_MAIL_PASSWORD_TEST}")
    private String password;

    @Bean
    public JavaMailSender mailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }
}
