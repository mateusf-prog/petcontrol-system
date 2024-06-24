package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
