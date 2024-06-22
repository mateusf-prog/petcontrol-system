package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.*;
import com.mateus.petcontrolsystem.infra.exceptions.InvalidTokenException;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import com.mateus.petcontrolsystem.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO body) {
        User user = repository.findByEmail(body.email()).orElseThrow(
                () -> new ResourceNotFoundException("USER NOT FOUND"));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new InvalidPasswordException("INVALID PASSWORD");
        }
        return new LoginResponseDTO(user.getName(), tokenService.generateToken(user));
    }

    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO body) {

        User user = repository.findByEmailOrCpfCnpj(body.email(), body.cpfCnpj());
        if (user != null) {
            if (user.getEmail().equals(body.email())) {
                throw new EntityAlreadyExistsException("USER ALREADY EXISTS BY EMAIL");
            }
            if (user.getCpfCnpj().equals(body.cpfCnpj()))
                throw new EntityAlreadyExistsException("USER ALREADY EXISTS BY CPF/CNPJ");
        }

        User newUser = mapper.convertValue(body, User.class);
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser = repository.save(newUser);
        String token = tokenService.generateToken(newUser);

        return new RegisterResponseDTO(newUser.getId(), newUser.getName(), token);
    }

    @Transactional
    public UpdateUserDTO update(UpdateUserDTO body, Long id) throws JsonMappingException {

        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) throw new EntityNotFoundException("ENTITY NOT FOUND");

        User existingUser = optionalUser.get();
        mapper.updateValue(existingUser, body);
        repository.save(existingUser);
        return mapper.convertValue(existingUser, UpdateUserDTO.class);
    }

    @Transactional
    public UserAccessDataResponseDTO updateAccessData(UserAccessDataRequestDTO body, Long id) {

        List<User> allUsers = repository.findAll();
        Optional<User> checkUserByEmail = allUsers.stream().filter(user -> user.getEmail().equals(body.email())).findFirst();
        if (checkUserByEmail.isPresent() && !checkUserByEmail.get().getId().equals(id)) {
            throw new EntityAlreadyExistsException("EMAIL ALREADY IN USE");
        }

        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) throw new EntityNotFoundException("ENTITY NOT FOUND");

        User user = optionalUser.get();
        if (!passwordEncoder.matches(body.actualPassword(), user.getPassword()))
            throw new InvalidPasswordException("OLD PASSWORD INVALID");

        user.setEmail(body.email());
        user.setPassword(passwordEncoder.encode(body.newPassword()));
        repository.save(user);
        return mapper.convertValue(user, UserAccessDataResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public void checkEmailExistsToRecoveryPassword(EmailToRecoverPasswordDTO body) {

        User entity = repository.findByEmail(body.email()).orElseThrow(
                () -> new EntityNotFoundException("USER NOT FOUND"));

        // todo: Here should be code for sent email code to recovery password
    }

    public String checkCodeToRecoverPassword(EmailCodeToRecoveryPasswordDTO body) {

        // todo: Retrieve the user
        User user = repository.findByEmail(body.email()).orElseThrow(
                () -> new EntityNotFoundException("USER NOT FOUND"));

        // todo: check the recovery code

        // todo: If the code is valid, generate a temporary token and return them
        String token = tokenService.generateTemporaryTokenToRecoveryPassword(user);

        return null;
    }

    @Transactional
    public EmailToRecoverPasswordDTO setNewPassword(NewPasswordToRecoveryAccount body) {

        String email = tokenService.validateToken(body.token());
        if (email == null) throw new InvalidTokenException("INVALID TOKEN");

        // if token is valid...
        User user = repository.findByEmail(body.email()).orElseThrow(
                () -> new EntityNotFoundException("USER NOT FOUND"));

        user.setPassword(passwordEncoder.encode(body.newPassword()));
        repository.save(user);
        return new EmailToRecoverPasswordDTO(user.getEmail());
    }
}
