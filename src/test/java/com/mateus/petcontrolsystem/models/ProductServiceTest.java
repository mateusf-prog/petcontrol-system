package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.petcontrolsystem.common.ProductConstants;
import com.mateus.petcontrolsystem.repositories.ProductRepository;
import com.mateus.petcontrolsystem.services.ProductService;
import com.mateus.petcontrolsystem.services.exceptions.EntityAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.stream.events.EntityDeclaration;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;
    @Mock
    private ObjectMapper mapper;

    @Test
    public void create_WithValidData_ReturnsNothing() {

        var validData = ProductConstants.provideValidProductDTO();

        when(repository.findByNameAndSupplier(validData.name(), validData.supplier())).thenReturn(Optional.empty());

        service.create(validData);

        verify(repository).save(any());
    }

    @Test
    public void create_WithAlreadyProduct_ThrowsException() {

        var validData = ProductConstants.provideValidProductDTO();
        var expectedResponse = ProductConstants.provideValidProduct();

        when(repository.findByNameAndSupplier(validData.name(), validData.supplier())).thenReturn(Optional.of(expectedResponse));

        assertThatThrownBy(() -> service.create(validData)).isInstanceOf(EntityAlreadyExistsException.class);
    }
}
