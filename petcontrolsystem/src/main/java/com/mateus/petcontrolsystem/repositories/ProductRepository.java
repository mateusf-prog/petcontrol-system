package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
