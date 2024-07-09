package com.mateus.petcontrolsystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.UpdateUserDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataRequestDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataResponseDTO;
import com.mateus.petcontrolsystem.services.UserService;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
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
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final long ANY_VALUE_ID_REQUEST = 5L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Test
    public void update_WithValidData_Returns200() throws Exception {

        var validBody = UserConstants.getValidUserUpdateDto();

        when(userService.update(validBody)).thenReturn(validBody);

        mockMvc.perform(put("/users")
                    .content(mapper.writeValueAsString(validBody))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @ParameterizedTest
    @MethodSource("com.mateus.petcontrolsystem.common.UserConstants#provideInvalidUserUpdateDTO")
    public void update_WithInvalidData_ReturnsBadRequest(UpdateUserDTO body) throws Exception {

        mockMvc.perform(put("/users")
                        .content(mapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_WithNonExistingUser_ReturnsNotFound() throws Exception {

        var validBody = UserConstants.getValidUserUpdateDto();

        when(userService.update(validBody)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(put("/users")
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAccessData_WithValidData_Returns200() throws Exception {

        var validBody = UserConstants.getValidUserAccessDataRequestDTO();
        var expectedResponse = new UserAccessDataResponseDTO("email@hotmail.com");

        when(userService.updateAccessData(validBody, ANY_VALUE_ID_REQUEST)).thenReturn(expectedResponse);

        mockMvc.perform(patch("/users/{id}", ANY_VALUE_ID_REQUEST)
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @ParameterizedTest
    @MethodSource("com.mateus.petcontrolsystem.common.UserConstants#provideInvalidUserAccessDataRequestDTO")
    public void updateAccessData_WithInvalidData_ReturnsBadRequest(UserAccessDataRequestDTO body) throws Exception {

        mockMvc.perform(patch("/users/{id}", ANY_VALUE_ID_REQUEST)
                        .content(mapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateAccessData_WithInvalidIdValue_ReturnsNotFound() throws Exception {

        var validBody = UserConstants.getValidUserAccessDataRequestDTO();

        when(userService.updateAccessData(validBody, ANY_VALUE_ID_REQUEST)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(patch("/users/{id}", ANY_VALUE_ID_REQUEST)
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAccessData_WithEmailAlreadyInUse_ReturnsConflict() throws Exception {

        var validBody = UserConstants.getValidUserAccessDataRequestDTO();

        when(userService.updateAccessData(validBody, ANY_VALUE_ID_REQUEST)).thenThrow(EntityAlreadyExistsException.class);

        mockMvc.perform(patch("/users/{id}", ANY_VALUE_ID_REQUEST)
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateAccessData_WithOldPasswordInvalid_ReturnsBadRequest() throws Exception {

        var validBody = UserConstants.getValidUserAccessDataRequestDTO();

        when(userService.updateAccessData(validBody, ANY_VALUE_ID_REQUEST)).thenThrow(InvalidPasswordException.class);

        mockMvc.perform(patch("/users/{id}", ANY_VALUE_ID_REQUEST)
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
