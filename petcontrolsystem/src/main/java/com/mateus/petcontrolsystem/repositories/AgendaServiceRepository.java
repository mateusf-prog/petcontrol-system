package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.AgendaService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaServiceRepository extends JpaRepository<AgendaService, Long> {
}
