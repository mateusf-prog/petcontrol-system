package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
