package com.mateus.petcontrolsystem.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mateus.petcontrolsystem.dto.UpdateUserDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataRequestDTO;
import com.mateus.petcontrolsystem.dto.UserAccessDataResponseDTO;
import com.mateus.petcontrolsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserDTO> update(@RequestBody UpdateUserDTO body, @PathVariable Long id) throws JsonMappingException {
        body = service.update(body, id);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserAccessDataResponseDTO> updateAccessData(
            @RequestBody UserAccessDataRequestDTO body, @PathVariable Long id) {
        UserAccessDataResponseDTO entity = service.updateAccessData(body, id);
        return ResponseEntity.ok(entity);
    }
}
