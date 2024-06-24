package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
