package com.mateus.petcontrolsystem.models;

import static com.mateus.petcontrolsystem.common.LoginConstants.LOGIN_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.LoginResponseDTO;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.UserService;
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

        // arrange
        User user = new User();
        user.setEmail(LOGIN_REQUEST.email());
        user.setPassword(LOGIN_REQUEST.password());
        user.setName("name-test");
        LoginResponseDTO expectedResponse = new LoginResponseDTO("name-test", "generated-token");

        when(repository.findByEmail(LOGIN_REQUEST.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(LOGIN_REQUEST.password(), LOGIN_REQUEST.password())).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn(expectedResponse.token());

        // act
        LoginResponseDTO sut = service.login(LOGIN_REQUEST);

        // assert
        assertThat(sut).isEqualTo(expectedResponse);
    }
}

