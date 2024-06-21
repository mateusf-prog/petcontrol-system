package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.LoginRequestDTO;
import com.mateus.petcontrolsystem.dto.LoginResponseDTO;
import com.mateus.petcontrolsystem.dto.RegisterRequestDTO;
import com.mateus.petcontrolsystem.dto.RegisterResponseDTO;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import com.mateus.petcontrolsystem.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = repository.findByEmail(dto.email()).orElseThrow(
                () -> new ResourceNotFoundException("USER NOT FOUND"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new InvalidPasswordException("INVALID PASSWORD");
        }
        return new LoginResponseDTO(user.getName(), tokenService.generateToken(user));
    }

    public RegisterResponseDTO register(RegisterRequestDTO body) {

        User user = repository.findByEmailOrCpfCnpj(body.email(), body.cpfCnpj());
        if (user != null) {
            if (user.getEmail().equals(body.email())) {
                throw new EntityAlreadyExistsException("USER ALREADY EXISTS BY EMAIL");
            }
            if (user.getCpfCnpj().equals(body.cpfCnpj())) {
                throw new EntityAlreadyExistsException("USER ALREADY EXISTS BY CPF/CNPJ");
            }
        }

        User newUser = mapper.convertValue(body, User.class);
        newUser.setPassword(passwordEncoder.encode(body.password()));
        repository.save(newUser);
        String token = tokenService.generateToken(newUser);

        return new RegisterResponseDTO(newUser.getName(), token);
    }
}
