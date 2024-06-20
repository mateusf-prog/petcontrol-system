package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register() {
        return null;
    }

}
