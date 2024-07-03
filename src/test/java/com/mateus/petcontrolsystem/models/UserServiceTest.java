package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.dto.*;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.UserService;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
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

/***
 * This class test, test only the logic operation in UserService. The objects data validation is tested in AuthControllerTest
 */
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

    // 'login' tests

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

    // 'register' tests

    @Test
    public void register_WithValidData_ReturnsRegisterResponseDTO() {

        var validUser = UserConstants.getValidRegisterRequestDTO();

        when(passwordEncoder.encode(validUser.password())).thenReturn("password-encoded");
        when(mapper.convertValue(validUser, User.class)).thenReturn(new User());

        service.register(validUser);

        verify(repository).save(any(User.class));
    }

    @Test
    public void register_WithAlreadyExistsByEmailAndCpfCnpj_ThrowsException() {

        var validUser = UserConstants.getValidRegisterRequestDTO();

        when(repository.findByEmailOrCpfCnpj(validUser.email(), validUser.cpfCnpj())).thenReturn(new User());

        assertThatThrownBy(() -> service.register(validUser)).isInstanceOf(RuntimeException.class);
        verify(repository).findByEmailOrCpfCnpj(validUser.email(), validUser.cpfCnpj());
    }

    // 'update' test

    @Test
    public void update_WithValidData_ReturnsUpdatedUserDTO() throws JsonMappingException {

        var validUser = UserConstants.getValidUserUpdateDto();
        var validEntity = UserConstants.getValidUser();

        var updatedEntity = UserConstants.getValidUser(); // represent the updated User
        var expectedResponse = UserConstants.getValidUserUpdateDto(); // represent the updated User as UpdateUserDTO

        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.of(validEntity));
        when(mapper.updateValue(validEntity, validUser)).thenReturn(updatedEntity);
        when(mapper.convertValue(updatedEntity, UpdateUserDTO.class)).thenReturn(expectedResponse);

        UpdateUserDTO sut = service.update(validUser);

        assertThat(sut).isEqualTo(expectedResponse);
    }

    @Test
    public void update_WithNotExistsUser_ThrowsException() {

        var validUser = UserConstants.getValidUserUpdateDto();

        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(validUser)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void update_WhenUpdateValueThrowsException_ThrowsJsonMappingException() throws JsonMappingException {
        var validUser = UserConstants.getValidUserUpdateDto();
        var validEntity = UserConstants.getValidUser();


        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.of(validEntity));
        when(mapper.updateValue(validEntity, validUser)).thenThrow(JsonMappingException.class);

        assertThatThrownBy(() -> service.update(validUser))
                .isInstanceOf(JsonMappingException.class);
    }

    @Test
    public void update_WhenConvertValueThrowsException_ThrowsJsonMappingException() throws JsonMappingException {
        var validUser = UserConstants.getValidUserUpdateDto();
        var validEntity = UserConstants.getValidUser();
        var updatedEntity = UserConstants.getValidUser();


        when(repository.findByCpfCnpj(validUser.cpfCnpj())).thenReturn(Optional.of(validEntity));
        when(mapper.updateValue(validEntity, validUser)).thenReturn(updatedEntity);
        when(mapper.convertValue(validEntity, UpdateUserDTO.class)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> service.update(validUser))
                .isInstanceOf(RuntimeException.class);
    }

    // 'updateAccessData' tests

    @Test
    public void updateAccessData_WithValidData_ReturnsUserAccessDataResponseDTO() {

        var validUserAccessData = UserConstants.getValidUserAccessDataDTO();
        var validEntity = UserConstants.getValidUser();
        var expectedResponse = new UserAccessDataResponseDTO(validUserAccessData.email());

        when(repository.findById(validEntity.getId())).thenReturn(Optional.of(validEntity));
        when(passwordEncoder.matches(validUserAccessData.actualPassword(), validEntity.getPassword())).thenReturn(true);
        when(passwordEncoder.encode(validUserAccessData.newPassword())).thenReturn("password-encoded");
        when(repository.save(validEntity)).thenReturn(validEntity);
        when(mapper.convertValue(validEntity, UserAccessDataResponseDTO.class)).thenReturn(expectedResponse);

        UserAccessDataResponseDTO sut = service.updateAccessData(validUserAccessData, validEntity.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.email()).isEqualTo(expectedResponse.email());
    }

    @Test
    public void updateAccessData_WithAlreadyExistsEmail_ThrowsException() {

        var validUserAccessData = UserConstants.getValidUserAccessDataDTO();

        // the value of id is irrelevant for this case
        when(repository.findByEmail(validUserAccessData.email())).thenReturn(Optional.of(new User()));

        assertThatThrownBy(() -> service.updateAccessData(validUserAccessData, 3L)).isInstanceOf(EntityAlreadyExistsException.class);
    }

    @Test
    public void updateAccessData_WithNotExistsUserById_ThrowsException() {

        var validUserAccessData = UserConstants.getValidUserAccessDataDTO();

        // the value of id is irrelevant for this case
        when(repository.findByEmail(validUserAccessData.email())).thenReturn(Optional.empty());
        when(repository.findById(3L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateAccessData(validUserAccessData, 3L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void updateAccessData_WithInvalidOldPassword_ThrowsException() {

        var validUserAccessData = UserConstants.getValidUserAccessDataDTO();
        var validEntity = UserConstants.getValidUser();

        // the value of id is irrelevant for this case
        when(repository.findByEmail(validUserAccessData.email())).thenReturn(Optional.empty());
        when(repository.findById(3L)).thenReturn(Optional.of(validEntity));
        when(passwordEncoder.matches(validUserAccessData.actualPassword(), validEntity.getPassword())).thenReturn(false);


        assertThatThrownBy(() -> service.updateAccessData(validUserAccessData, 3L)).isInstanceOf(InvalidPasswordException.class);
    }

}

