package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.UserLoginDTO;
import com.mateus.petcontrolsystem.dto.UserRegisterDTO;
import com.mateus.petcontrolsystem.dto.UserRegisterReturnDTO;
import com.mateus.petcontrolsystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> login(@RequestBody UserLoginDTO dto) {
        dto = service.userLogin(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterReturnDTO> register(@RequestBody UserRegisterDTO dto) {
        UserRegisterReturnDTO result = service.userRegister(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

}
