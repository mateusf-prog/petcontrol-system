package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.mateus.petcontrolsystem.common.ProductConstants;
import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByNameAndSupplier_WithExistentProduct_ReturnsProduct() {

        var validProduct = ProductConstants.provideValidProduct();
        testEntityManager.persistAndFlush(validProduct);

        Optional<Product> sut = repository.findByNameAndSupplier(validProduct.getName(), validProduct.getSupplier());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get().getName()).isEqualTo(validProduct.getName());
    }

    @Test
    public void findByNameAndSupplier_WithNonExistentProduct_ReturnsEmptyOptional() {

        var validProduct = ProductConstants.provideValidProduct();

        Optional<Product> sut = repository.findByNameAndSupplier(validProduct.getName(), validProduct.getSupplier());

        assertThat(sut).isEmpty();
    }

    @Test
    public void save_WithValidData_ReturnsProduct() {

        var validProduct = ProductConstants.provideValidProduct();
        testEntityManager.persistAndFlush(validProduct);

        Product sut = repository.save(validProduct);

        assertThat(sut.getName()).isEqualTo(validProduct.getName());
    }

    @Test
    public void save_WithInvalidData_ThrowsException() {

        var invalidProduct = ProductConstants.provideInvalidProduct();

        assertThatThrownBy(() -> repository.save(invalidProduct)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void findById_WithExistsProductById_ReturnsProduct() {

        var validProduct = ProductConstants.provideValidProduct();
        testEntityManager.persistAndFlush(validProduct);

        Optional<Product> sut = repository.findById(validProduct.getId());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get().getName()).isEqualTo(validProduct.getName());
    }

    @Test
    public void findById_WithNonExistentProductById_ReturnsEmptyOptional() {

        Optional<Product> sut = repository.findById(500L);

        assertThat(sut).isEmpty();
    }

    @Test
    public void findAll_WithExistentProducts_ReturnsListOfProducts() {

        List<Product> sut = repository.findAll();

        assertThat(sut).isNotEmpty();
    }

    @Test
    public void update_WithExistentProduct_ReturnsProductUpdated() {

        var validProduct = ProductConstants.provideValidProduct();
        testEntityManager.persistAndFlush(validProduct);

        var productFound = repository.findByNameAndSupplier(validProduct.getName(), validProduct.getSupplier());
        productFound.get().setName("Produto atualizado");

        var sut = repository.save(validProduct);

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(productFound.get().getName());
    }

    @Test
    public void delete_WithExistentProduct_ReturnsNothing() {

         var validProduct = ProductConstants.provideValidProduct();
         testEntityManager.persistAndFlush(validProduct);

         repository.delete(validProduct);

         Optional<Product> deletedProduct = repository.findById(validProduct.getId());
         assertThat(deletedProduct).isEmpty();
    }
}
