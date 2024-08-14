package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<Service> findByName(String name);
}
