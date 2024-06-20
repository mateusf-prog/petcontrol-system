package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.UserRegisterDTO;
import com.mateus.petcontrolsystem.dto.UserRegisterReturnDTO;
import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.exceptions.ResourceNotFoundException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final ObjectMapper mapper;

    public UserService(UserRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

}
