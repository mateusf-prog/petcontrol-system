package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.ProductDTO;
import com.mateus.petcontrolsystem.models.Product;
import com.mateus.petcontrolsystem.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapper mapper;

    public ProductService(ProductRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Optional<Product> entity = repository.findByNameAndSupplierAndCategory(dto.getName(), dto.getSupplier(), dto.getCategory());
        if (entity.isPresent()) {
            throw new RuntimeException("PRODUTO COM MESMO NOME E FORNECEDOR JÁ EXISTE");
        }
        Product product = mapper.convertValue(dto, Product.class);
        product = repository.save(product);

        return mapper.convertValue(product, ProductDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable) {

        Page<Product> result = repository.searchByName(name, pageable);
        return result.map(ProductDTO::new);
    }
}
