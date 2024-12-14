package com.mateus.petcontrolsystem.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mateus.petcontrolsystem.dto.GetUserDataResponseDTO;
import com.mateus.petcontrolsystem.dto.UpdateUserDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataRequestDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataResponseDTO;
import com.mateus.petcontrolsystem.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDataResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping
    public ResponseEntity<UpdateUserDTO> update(@RequestBody @Valid UpdateUserDTO body) throws JsonMappingException {
        body = service.update(body);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserAccessDataResponseDTO> updateAccessData(
            @RequestBody @Valid UserAccessDataRequestDTO body, @PathVariable Long id) {

        var entity = service.updateAccessData(body, id);
        return ResponseEntity.ok(entity);
    }
}
