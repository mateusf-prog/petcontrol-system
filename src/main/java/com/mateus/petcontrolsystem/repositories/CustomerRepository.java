package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
