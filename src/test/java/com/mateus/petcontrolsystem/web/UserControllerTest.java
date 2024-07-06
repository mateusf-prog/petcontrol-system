package com.mateus.petcontrolsystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.UpdateUserDTO;
import com.mateus.petcontrolsystem.services.UserService;
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

        var updateRequest = UserConstants.getValidUserUpdateDto();

        when(userService.update(updateRequest)).thenReturn(updateRequest);

        mockMvc.perform(put("/users")
                    .content(mapper.writeValueAsString(updateRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidUpdateUserDTO")
    public void update_WithInvalidData_ReturnsBadRequest(UpdateUserDTO body) throws Exception {

        mockMvc.perform(put("/users")
                        .content(mapper.writeValueAsString(body)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    public Stream<Arguments> provideInvalidUpdateUserDTO () {
        return UserConstants.provideInvalidUserUpdateDTO();
    }
}
