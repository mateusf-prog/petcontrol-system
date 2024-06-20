package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.UserRegisterDTO;
import com.mateus.petcontrolsystem.dto.UserRegisterReturnDTO;
import com.mateus.petcontrolsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;



    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterReturnDTO> register() {
        return null;
    }

}
