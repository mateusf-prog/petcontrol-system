package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.NewProductRequestDTO;
import com.mateus.petcontrolsystem.models.Product;
import com.mateus.petcontrolsystem.repositories.ProductRepository;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapper mapper;

    @Transactional
    public void create(NewProductRequestDTO dto) {

        Optional<Product> isExistingProduct = repository.findByNameAndSupplier(dto.name(), dto.supplier());
        if (isExistingProduct.isPresent()) throw new EntityAlreadyExistsException("PRODUCT ALREADY EXISTS");

        Product newProduct = mapper.convertValue(dto, Product.class);
        repository.save(newProduct);
    }
}
