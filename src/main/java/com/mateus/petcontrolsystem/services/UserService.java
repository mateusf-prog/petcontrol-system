package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.*;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.exceptions.*;
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
    private final EmailService emailService;

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO body) {
        var user = repository.findByEmail(body.email()).orElseThrow(
                () -> new ResourceNotFoundException("USER NOT FOUND"));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new InvalidPasswordException("INVALID PASSWORD");
        }

        return new LoginResponseDTO(user.getId(), tokenService.generateToken(user));
    }

    @Transactional
    public void register(RegisterRequestDTO body) {

        var user = repository.findByEmailOrCpfCnpj(body.email(), body.cpfCnpj());

        if (user != null) throw new EntityAlreadyExistsException("USER ALREADY EXISTS");

        var newUser = mapper.convertValue(body, User.class);
        newUser.setPassword(passwordEncoder.encode(body.password()));
        repository.save(newUser);
        emailService.sendWelcomeMessageToNewUser(newUser.getEmail(), newUser.getName());
    }

    @Transactional
    public UpdateUserDTO update(UpdateUserDTO body) throws JsonMappingException {

        var entity = repository.findByCpfCnpj(body.cpfCnpj()).orElseThrow(
                () -> new EntityNotFoundException("ENTITY NOT FOUND"));

        var userUpdated = mapper.updateValue(entity, body);
        repository.save(userUpdated);

        return mapper.convertValue(userUpdated, UpdateUserDTO.class);
    }

    @Transactional
    public UserAccessDataResponseDTO updateAccessData(UserAccessDataRequestDTO body, Long id) {

        Optional<User> existsByEmail = repository.findByEmail(body.email());
        if (existsByEmail.isPresent()) throw new EntityAlreadyExistsException("EMAIL ALREADY IN USE");

        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) throw new EntityNotFoundException("ENTITY NOT FOUND");

        var user = optionalUser.get();
        if (!passwordEncoder.matches(body.actualPassword(), user.getPassword())) throw new InvalidPasswordException("INVALID CURENT PASSWORD");

        user.setEmail(body.email());
        user.setPassword(passwordEncoder.encode(body.newPassword()));
        repository.save(user);
        emailService.sendUpdateDataToEmail(body.email(), user.getName());

        return mapper.convertValue(user, UserAccessDataResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public GetUserDataResponseDTO getUserById(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ENTITY NOT FOUND"));

        return mapper.convertValue(entity, GetUserDataResponseDTO.class);
    }
}
