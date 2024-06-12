package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
