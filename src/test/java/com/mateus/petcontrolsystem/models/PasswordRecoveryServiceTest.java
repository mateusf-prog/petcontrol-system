package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mateus.petcontrolsystem.common.PasswordRecoveryConstants;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.password.CodeReceivedEmailResponseDTO;
import com.mateus.petcontrolsystem.dto.password.CodeReceivedInEmailRequestDTO;
import com.mateus.petcontrolsystem.dto.password.NewPasswordToRecoveryAccountDTO;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.repositories.PasswordRecoveryRepository;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.EmailService;
import com.mateus.petcontrolsystem.services.PasswordRecoveryService;
import com.mateus.petcontrolsystem.services.exceptions.ExpiredCodeException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidCodeException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidProcessRecoveryPasswordException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * This class contains unit tests for the PasswordRecoveryService class.
 * It uses UserConstants to create a valid User entity.
 */
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
    @Mock
    private CodeReceivedInEmailRequestDTO requestDTO;
    @Mock
    private PasswordRecovery passwordRecovery;

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

    @Test
    public void sendCodeToEmail_WithNonExistingUserByEmail_ThrowsException() {

        var invalidDTO = PasswordRecoveryConstants.getInvalidEmailToRecoverPasswordDTO();

        assertThatThrownBy(() -> service.sendCodeToEmail(invalidDTO)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void validateCodeReceivedInEmail_WithValidCode_ReturnsCodeReceivedEmailResponseDTO() {

        var validRequestBody = PasswordRecoveryConstants.getValidCodeReceivedEmailRequestDTO();
        var validUser = UserConstants.getValidUser();
        var mockPasswordRecovery = PasswordRecoveryConstants.getValidPasswordEntity();
        var expectedResponse = new CodeReceivedEmailResponseDTO("generated-token-test");

        when(userRepository.findByEmail(validRequestBody.email())).thenReturn(Optional.of(validUser));
        when(passwordRecoveryRepository.findByUserEmail(validUser.getEmail())).thenReturn(Optional.of(mockPasswordRecovery));
        when(tokenService.generateTemporaryTokenToRecoveryPassword(validUser)).thenReturn("generated-token-test");
        when(passwordRecoveryRepository.save(mockPasswordRecovery)).thenReturn(mockPasswordRecovery);

        PasswordRecoveryService spyService = spy(service);
        doNothing().when(spyService).validateCodeReceived(validRequestBody, mockPasswordRecovery);

        var sut = spyService.validateCodeReceivedInEmail(validRequestBody);

        assertThat(sut).isEqualTo(expectedResponse);
    }

    @Test
    public void validateCodeReceivedInEmail_WithNonExistingUserByEmail_ThrowsException() {

        var validRequestBody = PasswordRecoveryConstants.getValidCodeReceivedEmailRequestDTO();

        when(userRepository.findByEmail(validRequestBody.email())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.validateCodeReceivedInEmail(validRequestBody)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void validateCodeReceivedInEmail_WithNonExistentRequest_ThrowsException() {

        var validRequestBody = PasswordRecoveryConstants.getValidCodeReceivedEmailRequestDTO();
        var validUser = UserConstants.getValidUser();
        validUser.setEmail(validRequestBody.email());

        when(userRepository.findByEmail(validRequestBody.email())).thenReturn(Optional.of(validUser));
        when(passwordRecoveryRepository.findByUserEmail(validRequestBody.email())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.validateCodeReceivedInEmail(validRequestBody)).isInstanceOf(InvalidProcessRecoveryPasswordException.class);
    }

    @Test
    public void setNewPassword_WithValidData_ReturnsEmailToRecoveryPasswordDTO() {

        var validBody = PasswordRecoveryConstants.getValidNewPasswordToRecoveryAccountDTO();
        var expectedResponse = PasswordRecoveryConstants.getValidEmailToRecoverPasswordDTO();
        var validUser = UserConstants.getValidUser();
        validUser.setEmail(validBody.email());

        when(userRepository.findByEmail(validBody.email())).thenReturn(Optional.of(validUser));
        when(passwordEncoder.encode(validBody.newPassword())).thenReturn("password-encoded");
        when(userRepository.save(any(User.class))).thenReturn(any(User.class));

        var sut = service.setNewUserPassword(validBody);

        assertThat(sut.email()).isEqualTo(expectedResponse.email());
    }

    @Test
    public void setNewPassword_WithInvalidData_ThrowsException() {

        assertThatThrownBy(() -> service.setNewUserPassword(new NewPasswordToRecoveryAccountDTO("", "")))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void setNewPassword_WithNonExistingUserByEmail_ThrowsEntityNotFoundException() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.setNewUserPassword(new NewPasswordToRecoveryAccountDTO("", "")))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void generateCodeToSendToEmail_GenerateValidCode_ReturnsCodeWithFiveNumbers() {

        var sut = service.generateCodeToSendToEmail();

        assertThat(sut).hasSize(5);
    }

    @Test
    public void validateCodeReceived_WithNullRecoveryCode_ThrowsInvalidCodeException() {
        when(passwordRecovery.getRecoveryCode()).thenReturn(null);

        assertThrows(InvalidCodeException.class, () -> service.validateCodeReceived(requestDTO, passwordRecovery));
    }

    @Test
    public void validateCodeReceived_WithInvalidRecoveryCode_ThrowsInvalidCodeException() {
        when(passwordRecovery.getRecoveryCode()).thenReturn("12345");
        when(requestDTO.code()).thenReturn("54321");

        assertThrows(InvalidCodeException.class, () -> service.validateCodeReceived(requestDTO, passwordRecovery));
    }

    @Test
    public void validateCodeReceived_WithExpiredCode_ThrowsExpiredCodeException() {
        when(passwordRecovery.getRecoveryCode()).thenReturn("12345");
        when(requestDTO.code()).thenReturn("12345");
        when(passwordRecovery.getCodeCreatedAt()).thenReturn(LocalDateTime.now().minusMinutes(11).toInstant(ZoneOffset.of("-3")));

        assertThrows(ExpiredCodeException.class, () -> service.validateCodeReceived(requestDTO, passwordRecovery));
    }
}



























