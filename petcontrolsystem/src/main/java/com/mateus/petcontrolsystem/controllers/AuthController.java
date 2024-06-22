package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.*;
import com.mateus.petcontrolsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body) {
        LoginResponseDTO response = userService.login(body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        RegisterResponseDTO response = userService.register(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/passwordRecovery")
    public ResponseEntity<Void> checkEmailExistsToRecoveryPassword(@RequestBody EmailToRecoverPasswordDTO body) {
        userService.checkEmailExistsToRecoveryPassword(body);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirmCode")
    public ResponseEntity<String> checkCodeToRecoverPassword(@RequestBody EmailCodeToRecoveryPasswordDTO body) {
        String token = userService.checkCodeToRecoverPassword(body);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/setNewPassword")
    public ResponseEntity<EmailToRecoverPasswordDTO> setNewPassword(@RequestBody NewPasswordToRecoveryAccount body) {
        EmailToRecoverPasswordDTO result = userService.setNewPassword(body);
        return ResponseEntity.ok().body(result);
    }
}
