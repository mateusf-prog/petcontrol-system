package com.mateus.petcontrolsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.models.Product;
import com.mateus.petcontrolsystem.models.Service;
import com.mateus.petcontrolsystem.dto.ServiceDTO;
import com.mateus.petcontrolsystem.repositories.ServiceRepository;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/** This class is the class of Service domain, this name is to avoid name duplication */

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceManagement {

    private final ServiceRepository repository;
    private final ObjectMapper mapper;

    @Transactional(readOnly = true)
    public Page<ServiceDTO> findAll(Pageable page) {
        Page<Service> result = repository.findAll(page);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("SERVICES NOT FOUND");
        }

        return result.map(x -> mapper.convertValue(x, ServiceDTO.class));
    }

    @Transactional
    public void create(ServiceDTO dto) {
        Optional<Service> isExistsServiceByName = repository.findByName(dto.name());
        if (isExistsServiceByName.isPresent()) {
            throw new EntityAlreadyExistsException(("ALREADY EXISTS SERVICE WITH THE SAME NAME"));
        }
        repository.save(mapper.convertValue(dto, Service.class));
    }

    @Transactional
    public void update(ServiceDTO dto) {
        Optional<Service> isExistsServiceByName = repository.findByName(dto.name());
        if (isExistsServiceByName.isPresent()) {
            throw new EntityAlreadyExistsException(("ALREADY EXISTS SERVICE WITH THE SAME NAME"));
        }
        repository.save(mapper.convertValue(dto, Service.class));
    }

    @Transactional
    public void delete(Long id) {
        Service result = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("SERVICE NOT FOUND"));
        repository.delete(result);
    }
}
