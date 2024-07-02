package com.mateus.petcontrolsystem.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.LoginRequestDTO;
import com.mateus.petcontrolsystem.dto.LoginResponseDTO;
import com.mateus.petcontrolsystem.services.PasswordRecoveryService;
import com.mateus.petcontrolsystem.services.UserService;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void login_WithInvalidData_ReturnsBadRequest() throws Exception {
        var emptyData = new LoginRequestDTO("", "");
        var nullData = new LoginRequestDTO(null, null);
        var invalidEmail = new LoginRequestDTO("invalidemailpattern", "Password1234");
        var invalidPassword = new LoginRequestDTO("validemail@email.com", "invalidpasswordpattern");

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(emptyData))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(nullData))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(invalidEmail))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(invalidPassword))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void login_WithNonExistingUser_ReturnsNotFound() throws Exception {

        var body = UserConstants.getValidLoginRequestDTO();

        when(userService.login(any())).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(body))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void login_WithWrongPassword_ReturnsBadRequest() throws Exception {

        var validBody = UserConstants.getValidLoginRequestDTO();

        when(userService.login(any())).thenThrow(InvalidPasswordException.class);

        mockMvc.perform(
                post("/auth/login")
                    .content(mapper.writeValueAsString(validBody))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
