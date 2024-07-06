package com.mateus.petcontrolsystem.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.UpdateUserDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataRequestDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataResponseDTO;
import com.mateus.petcontrolsystem.services.UserService;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
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
    @MethodSource("provideInvalidUserUpdateDTO")
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
        var validId = 3L;
        var expectedResponse = new UserAccessDataResponseDTO("email@hotmail.com");

        when(userService.updateAccessData(validBody, validId)).thenReturn(expectedResponse);

        mockMvc.perform(patch("/users/{id}", validId)
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidUserAccessDataRequestDTO")
    public void updateAccessData_WithInvalidData_ReturnsBadRequest(UserAccessDataRequestDTO body) throws Exception {

        var validId = 3L;

        mockMvc.perform(patch("/users/{id}", validId)
                        .content(mapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateAccessData_WithInvalidIdValue_ReturnsNotFound() throws Exception {

        var validBody = UserConstants.getValidUserAccessDataRequestDTO();
        var anyId = 9L;

        when(userService.updateAccessData(validBody, anyId)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(patch("/users/{id}", anyId)
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAccessData_WithEmailAlreadyInUse_ReturnsConflict() throws Exception {

        var validBody = UserConstants.getValidUserAccessDataRequestDTO();
        var anyId = 9L;

        when(userService.updateAccessData(validBody, anyId)).thenThrow(EntityAlreadyExistsException.class);

        mockMvc.perform(patch("/users/{id}", anyId)
                        .content(mapper.writeValueAsString(validBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    public void updateAccessData_WithOldPasswordInvalid_() {
        //todo implement and rename
    }


    public Stream<Arguments> provideInvalidUserUpdateDTO () {
        return UserConstants.provideInvalidUserUpdateDTO();
    }


    public Stream<Arguments> provideInvalidUserAccessDataRequestDTO () {
        return UserConstants.provideInvalidUserAccessDataRequestDTO();
    }
}
