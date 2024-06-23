package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.PasswordRecovery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecovery, Long> {

    Optional<PasswordRecovery> findByUserEmail(String email);
}
