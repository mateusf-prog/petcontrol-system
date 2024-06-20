package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final ObjectMapper mapper;

    public UserService(UserRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
