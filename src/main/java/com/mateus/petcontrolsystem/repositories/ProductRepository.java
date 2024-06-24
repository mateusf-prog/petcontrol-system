package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Product;
import com.mateus.petcontrolsystem.models.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT obj FROM Product obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);

    Optional<Product> findByNameAndSupplierAndCategory(String name, String supplier, Category category);
}
