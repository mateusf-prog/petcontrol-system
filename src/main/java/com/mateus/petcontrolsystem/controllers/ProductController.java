package com.mateus.petcontrolsystem.controllers;

import com.mateus.petcontrolsystem.dto.NewProductRequestDTO;
import com.mateus.petcontrolsystem.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid NewProductRequestDTO body) {
        service.create(body);
    }
}
