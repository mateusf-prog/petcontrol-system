package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.LoginRequestDTO;
import com.mateus.petcontrolsystem.dto.LoginResponseDTO;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.UserService;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import com.mateus.petcontrolsystem.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private ObjectMapper mapper;
    @Mock
    private TokenService tokenService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void login_WithValidData_ReturnsLoginResponseDTO() {

        var validUser = UserConstants.getValidUser();

        LoginRequestDTO dto = new LoginRequestDTO(validUser.getEmail(), validUser.getPassword());
        LoginResponseDTO expectedResponse = new LoginResponseDTO(validUser.getName(), "generated-token");

        when(repository.findByEmail(validUser.getEmail())).thenReturn(Optional.of(validUser));
        when(passwordEncoder.matches(validUser.getPassword(),validUser.getPassword())).thenReturn(true);
        when(tokenService.generateToken(validUser)).thenReturn(expectedResponse.token());

        LoginResponseDTO sut = service.login(dto);

        assertThat(sut).isEqualTo(expectedResponse);
    }

    @Test
    public void login_WithInvalidData_ThrowsException() {

        var user = UserConstants.getInvalidUser();
        LoginRequestDTO dto = new LoginRequestDTO(user.getEmail(), user.getPassword());

        assertThatThrownBy(() -> service.login(dto)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void login_WithNonExistingUser_ThrowsException() {

        var validUser = UserConstants.getValidUser();
        LoginRequestDTO dto = new LoginRequestDTO(validUser.getEmail(), validUser.getPassword());

        when(repository.findByEmail(validUser.getEmail())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.login(dto)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void login_WithInvalidPassword_ThrowsException() {

        var validUser = UserConstants.getValidUser();
        LoginRequestDTO dto = new LoginRequestDTO(validUser.getEmail(), validUser.getPassword());

        when(repository.findByEmail(validUser.getEmail())).thenReturn(Optional.of(validUser));
        when(passwordEncoder.matches(dto.password(), validUser.getPassword())).thenReturn(false);

        assertThatThrownBy(() -> service.login(dto)).isInstanceOf(InvalidPasswordException.class);
    }
}

