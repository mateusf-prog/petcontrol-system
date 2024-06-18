package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.UserLoginDTO;
import com.mateus.petcontrolsystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserLoginDTO> login(@RequestBody UserLoginDTO dto) {
        dto = service.userLogin(dto);
        return ResponseEntity.ok(dto);
    }

}
