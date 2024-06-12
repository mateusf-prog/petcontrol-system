package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Treatmentrepository extends JpaRepository<Treatment,Long> {
}
