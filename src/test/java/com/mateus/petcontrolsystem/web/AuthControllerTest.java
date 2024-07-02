package com.mateus.petcontrolsystem.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.LoginResponseDTO;
import com.mateus.petcontrolsystem.services.PasswordRecoveryService;
import com.mateus.petcontrolsystem.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(AuthController.class)
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
    public void login_WithValidData_Returns200OK() throws Exception {

        var validBodyRequest = UserConstants.getValidLoginRequestDTO();
        var expectedResponse = new LoginResponseDTO("generated-token");

        when(userService.login(validBodyRequest)).thenReturn(expectedResponse);

        mockMvc.perform(post("/auth/login")
                .content(mapper.writeValueAsString(validBodyRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.token").value(expectedResponse.token()));
    }
}
