package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
