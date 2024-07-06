package com.mateus.petcontrolsystem.models;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.mateus.petcontrolsystem.common.PasswordRecoveryConstants;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.repositories.PasswordRecoveryRepository;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.EmailService;
import com.mateus.petcontrolsystem.services.PasswordRecoveryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PasswordRecoveryServiceTest {

    @InjectMocks
    private PasswordRecoveryService service;
    @Mock
    private PasswordRecoveryRepository passwordRecoveryRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenService tokenService;
    @Mock
    private EmailService emailService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void sendCodeToEmail_WithValidUser_EmailServiceIsCalled() {

        var validRequestBody = PasswordRecoveryConstants.getValidEmailToRecoverPasswordDTO();
        var mockPasswordRecovery = PasswordRecoveryConstants.getValidPasswordEntity();
        var fixedCode = "12345"; // fix number code to test

        when(userRepository.findByEmail(validRequestBody.email())).thenReturn(Optional.of(new User()));
        when(passwordRecoveryRepository.save(any(PasswordRecovery.class))).thenReturn(mockPasswordRecovery);

        PasswordRecoveryService spyService = spy(service);
        doReturn(fixedCode).when(spyService).generateCodeToSendToEmail();

        spyService.sendCodeToEmail(validRequestBody);

        verify(emailService).sendCodeToEmail(eq(fixedCode), eq(validRequestBody.email()));
    }
}
