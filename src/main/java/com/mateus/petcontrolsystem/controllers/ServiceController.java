package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.ProductDTO;
import com.mateus.petcontrolsystem.dto.ServiceDTO;
import com.mateus.petcontrolsystem.services.ServiceManagement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceManagement service;

    @GetMapping
    public ResponseEntity<Page<ServiceDTO>> findAll(Pageable pageable) {
        Page<ServiceDTO> result = service.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ServiceDTO body) {
        service.create(body);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid ServiceDTO dto) {
        service.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
