package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.*;
import com.mateus.petcontrolsystem.dto.password.CodeReceivedEmailResponseDTO;
import com.mateus.petcontrolsystem.dto.password.CodeReceivedInEmailRequestDTO;
import com.mateus.petcontrolsystem.dto.password.EmailToRecoverPasswordDTO;
import com.mateus.petcontrolsystem.dto.password.NewPasswordToRecoveryAccount;
import com.mateus.petcontrolsystem.services.PasswordRecoveryService;
import com.mateus.petcontrolsystem.services.UserService;
import jakarta.validation.Valid;
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
    private final PasswordRecoveryService recoveryPasswordService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO body) {
        LoginResponseDTO response = userService.login(body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDTO body) {
        userService.register(body);
        return ResponseEntity.created(null).body(null);
    }

    @PostMapping("/passwordRecover")
    public ResponseEntity<Void> sendCodeToEmail(@RequestBody @Valid EmailToRecoverPasswordDTO body) {
        recoveryPasswordService.sendCodeToEmail(body);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirmCode")
    public ResponseEntity<CodeReceivedEmailResponseDTO> validateCodeReceivedInEmail(@RequestBody @Valid CodeReceivedInEmailRequestDTO body) {
        CodeReceivedEmailResponseDTO token = recoveryPasswordService.validateCodeReceivedInEmail(body);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/setNewPassword")
    public ResponseEntity<EmailToRecoverPasswordDTO> setNewPassword(@RequestBody @Valid NewPasswordToRecoveryAccount body) {
        EmailToRecoverPasswordDTO result = recoveryPasswordService.setNewUserPassword(body);
        return ResponseEntity.ok().body(result);
    }
}
