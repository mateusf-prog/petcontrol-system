package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.LoginRequestDTO;
import com.mateus.petcontrolsystem.dto.LoginResponseDTO;
import com.mateus.petcontrolsystem.dto.UpdateUserDTO;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.UserService;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import com.mateus.petcontrolsystem.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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

    // login tests
    @Test
    public void login_WithValidData_ReturnsLoginResponseDTO() {

        var validUser = UserConstants.getValidUser();

        LoginRequestDTO dto = new LoginRequestDTO(validUser.getEmail(), validUser.getPassword());
        LoginResponseDTO expectedResponse = new LoginResponseDTO("generated-token");

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

    // register tests
    @Test
    public void register_WithValidData_ReturnsRegisterResponseDTO() {

        var validUser = UserConstants.getValidRegisterRequestDTO();

        when(passwordEncoder.encode(validUser.password())).thenReturn("password-encoded");
        when(mapper.convertValue(validUser, User.class)).thenReturn(new User());

        service.register(validUser);

        verify(repository).save(any(User.class));
    }

    @Test
    public void register_WithInvalidData_ThrowsException() {

        var invalidUser = UserConstants.getInValidRegisterRequestDTO();

        assertThatThrownBy(() -> service.register(invalidUser)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void register_WithAlreadyExistsByEmailAndCpfCnpj_ThrowsException() {

        var validUser = UserConstants.getValidRegisterRequestDTO();

        when(repository.findByEmailOrCpfCnpj(validUser.email(), validUser.cpfCnpj())).thenReturn(new User());

        assertThatThrownBy(() -> service.register(validUser)).isInstanceOf(RuntimeException.class);
        verify(repository).findByEmailOrCpfCnpj(validUser.email(), validUser.cpfCnpj());
    }

    @Test
    public void update_WithValidData_ReturnsUpdatedUserDTO() throws JsonMappingException {

        var validUser = UserConstants.getUpdateUserDTO();
        var validEntity = UserConstants.getValidUser();

        var updatedEntity = UserConstants.getValidUser(); // represent the updated User
        var expectedResponse = UserConstants.getUpdateUserDTO(); // represent the updated User as UpdateUserDTO

        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.of(validEntity));
        when(mapper.updateValue(validEntity, validUser)).thenReturn(updatedEntity);
        when(mapper.convertValue(updatedEntity, UpdateUserDTO.class)).thenReturn(expectedResponse);

        UpdateUserDTO sut = service.update(validUser);

        assertThat(sut).isEqualTo(expectedResponse);
    }

    @Test
    public void update_WithNotExistsUser_ThrowsException() throws JsonMappingException {

        var validUser = UserConstants.getUpdateUserDTO();

        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(validUser)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void update_WhenUpdateValueThrowsException_ThrowsJsonMappingException() throws JsonMappingException {
        var validUser = UserConstants.getUpdateUserDTO();
        var validEntity = UserConstants.getValidUser();


        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.of(validEntity));
        when(mapper.updateValue(validEntity, validUser)).thenThrow(JsonMappingException.class);

        assertThatThrownBy(() -> service.update(validUser))
                .isInstanceOf(JsonMappingException.class);
    }

    @Test
    public void update_WhenConvertValueThrowsException_ThrowsJsonMappingException() throws JsonMappingException {
        var validUser = UserConstants.getUpdateUserDTO();
        var validEntity = UserConstants.getValidUser();
        var updatedEntity = UserConstants.getValidUser();


        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.of(validEntity));
        when(mapper.updateValue(validEntity, validUser)).thenReturn(updatedEntity);
        when(mapper.convertValue(validEntity, UpdateUserDTO.class)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> service.update(validUser))
                .isInstanceOf(RuntimeException.class);
    }



}

