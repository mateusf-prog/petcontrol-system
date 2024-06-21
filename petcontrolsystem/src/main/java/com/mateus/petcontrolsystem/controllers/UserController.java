package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.UserUpdateDTO;
import com.mateus.petcontrolsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PutMapping
    public ResponseEntity<UserUpdateDTO> update(@RequestBody UserUpdateDTO body) {
        body = service.update(body);
        return ResponseEntity.ok(body);
    }
}
