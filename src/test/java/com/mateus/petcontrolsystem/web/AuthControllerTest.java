package com.mateus.petcontrolsystem.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.PasswordRecoveryConstants;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.LoginRequestDTO;
import com.mateus.petcontrolsystem.dto.LoginResponseDTO;
import com.mateus.petcontrolsystem.dto.RegisterRequestDTO;
import com.mateus.petcontrolsystem.dto.password.CodeReceivedEmailResponseDTO;
import com.mateus.petcontrolsystem.dto.password.CodeReceivedInEmailRequestDTO;
import com.mateus.petcontrolsystem.dto.password.EmailToRecoverPasswordDTO;
import com.mateus.petcontrolsystem.dto.password.NewPasswordToRecoveryAccountDTO;
import com.mateus.petcontrolsystem.services.PasswordRecoveryService;
import com.mateus.petcontrolsystem.services.UserService;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidProcessRecoveryPasswordException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordRecoveryService passwordRecoveryService;

    @Test
    public void login_WithValidData_Returns200() throws Exception {

        var validBodyRequest = UserConstants.getValidLoginRequestDTO();
        var expectedResponse = new LoginResponseDTO("generated-token");

        when(userService.login(validBodyRequest)).thenReturn(expectedResponse);

        mockMvc.perform(post("/auth/login")
                    .content(mapper.writeValueAsString(validBodyRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.token").value(expectedResponse.token()));
    }

    @ParameterizedTest
    @MethodSource("com.mateus.petcontrolsystem.common.UserConstants#provideInvalidLoginRequestDTO")
    public void login_WithInvalidData_ReturnsBadRequest(LoginRequestDTO body) throws Exception {

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(body))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void login_WithNonExistingUser_ReturnsNotFound() throws Exception {

        var body = UserConstants.getValidLoginRequestDTO();

        when(userService.login(any())).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(body))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void login_WithWrongPassword_ReturnsBadRequest() throws Exception {

        var validBody = UserConstants.getValidLoginRequestDTO();

        when(userService.login(any())).thenThrow(InvalidPasswordException.class);

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(validBody))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void register_WithValidData_Returns201Created() throws Exception {

        var validBodyRequest = UserConstants.getValidRegisterRequestDTO();

        doNothing().when(userService).register(validBodyRequest);

        mockMvc.perform(post("/auth/register")
                        .content(mapper.writeValueAsString(validBodyRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));
    }

    @ParameterizedTest
    @MethodSource("com.mateus.petcontrolsystem.common.UserConstants#provideInvalidRegisterRequestDTO")
    public void register_WithInvalidData_ReturnsBadRequest(RegisterRequestDTO request) throws Exception {

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().is(400));
    }

    @Test
    public void register_WithExistingEmailOrCpfCnpj_ReturnsConflict() throws Exception {

        var validUser = UserConstants.getValidRegisterRequestDTO();

        doThrow(EntityAlreadyExistsException.class).when(userService).register(validUser);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validUser)))
                .andExpect(status().is(409));
    }

    @Test
    public void sendCodeToEmail_WithValidData_Returns200Ok() throws Exception {

        var validBody = PasswordRecoveryConstants.getValidEmailToRecoverPasswordDTO();

        mockMvc.perform(post("/auth/passwordRecover")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validBody)))
                .andExpect(status().is(200));
    }

    @Test
    public void sendCodeToEmail_WithNonExistingUser_ReturnsNotFound() throws Exception{

        var body = PasswordRecoveryConstants.getValidEmailToRecoverPasswordDTO();

        doThrow(EntityNotFoundException.class).when(passwordRecoveryService).sendCodeToEmail(body);

        mockMvc.perform(post("/auth/passwordRecover")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(404));
    }

    @ParameterizedTest
    @MethodSource("com.mateus.petcontrolsystem.common.PasswordRecoveryConstants#provideInvalidEmailToRecoverPasswordDTO")
    public void sendCodeToEmail_WithInvalidData_Returns200Ok(EmailToRecoverPasswordDTO body) throws Exception {

        mockMvc.perform(post("/auth/passwordRecover")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(400));
    }

    @Test
    public void validateCodeReceivedInEmail_WithValidBody_Returns200OK() throws Exception{

        var body = PasswordRecoveryConstants.getValidCodeReceivedEmailRequestDTO();
        var expectedResponse = new CodeReceivedEmailResponseDTO("generated-token");

        when(passwordRecoveryService.validateCodeReceivedInEmail(body)).thenReturn(expectedResponse);

        mockMvc.perform(post("/auth/confirmCode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.token").value(expectedResponse.token()));
    }

    @ParameterizedTest
    @MethodSource("com.mateus.petcontrolsystem.common.PasswordRecoveryConstants#provideInvalidCodeReceivedEmailRequestDTO")
    public void validateCodeReceivedInEmail_WithInvalidBody_ReturnsBadRequest(CodeReceivedInEmailRequestDTO body) throws Exception{

        mockMvc.perform(post("/auth/confirmCode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(400));
    }

    @Test
    public void validateCodeReceivedInEmail_WithNonExistingUser_ReturnsNotFound() throws Exception {

        var body = PasswordRecoveryConstants.getValidCodeReceivedEmailRequestDTO();

        doThrow(EntityNotFoundException.class).when(passwordRecoveryService).validateCodeReceivedInEmail(body);

        mockMvc.perform(post("/auth/confirmCode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(404));
    }

    // this case happen when not existing a request of password recovery
    @Test
    public void validateCodeReceivedInEmail_InvalidRequest_ReturnsBadRequest() throws Exception {

        var body = PasswordRecoveryConstants.getValidCodeReceivedEmailRequestDTO();

        when(passwordRecoveryService.validateCodeReceivedInEmail(body)).thenThrow(InvalidProcessRecoveryPasswordException.class);

        mockMvc.perform(post("/auth/confirmCode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(400));
    }

    @Test
    public void setNewPassword_WithValidBody_Returns200OK() throws Exception {

        var validBody = PasswordRecoveryConstants.getValidNewPasswordToRecoveryAccountDTO();

        mockMvc.perform(post("/auth/setNewPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validBody)))
                .andExpect(status().is(200));
    }

    @Test
    public void setNewPassword_WithNonExistingUser_ReturnsNotFound() throws Exception {

        var validBody = PasswordRecoveryConstants.getValidNewPasswordToRecoveryAccountDTO();

        when(passwordRecoveryService.setNewUserPassword(validBody)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(post("/auth/setNewPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validBody)))
                .andExpect(status().is(404));
    }

    @ParameterizedTest
    @MethodSource("com.mateus.petcontrolsystem.common.PasswordRecoveryConstants#provideNewPasswordToRecoveryAccountDTO")
    public void setNewPassword_WithInvalidBody_Returns200OK(NewPasswordToRecoveryAccountDTO body) throws Exception {

        mockMvc.perform(post("/auth/setNewPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(400));
    }
}
