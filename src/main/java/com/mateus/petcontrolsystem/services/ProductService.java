package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.dto.ProductDTO;
import com.mateus.petcontrolsystem.models.Product;
import com.mateus.petcontrolsystem.repositories.ProductRepository;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapper mapper;

    @Transactional
    public void create(ProductDTO dto) {
        Optional<Product> isExistingProduct = repository.findByNameAndSupplier(dto.name(), dto.supplier());
        if (isExistingProduct.isPresent()) throw new EntityAlreadyExistsException("PRODUCT ALREADY EXISTS");

        var newProduct = mapper.convertValue(dto, Product.class);
        repository.save(newProduct);
    }

    @Transactional
    public void update(ProductDTO dto) throws Exception {
        if (dto.id() == null) throw new EntityNotFoundException("PRODUCT NOT FOUND");
        var entity = repository.findById(dto.id()).orElseThrow(() -> new EntityNotFoundException("PRODUCT NOT FOUND"));

        entity= mapper.updateValue(entity, dto);
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        if (result.isEmpty()) throw new EntityNotFoundException("EMPTY LIST");

        return result.map(x -> mapper.convertValue(x, ProductDTO.class));
    }

    @Transactional
    public void delete(Long id) {
        var result = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("PRODUCT NOT FOUND"));
        repository.delete(result);
    }
}




























































