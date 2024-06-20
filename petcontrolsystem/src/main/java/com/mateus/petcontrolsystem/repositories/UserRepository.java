package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.cpfCnpj = :cpfCnpj")
    List<User> findByEmailOrCpfCnpj(@Param("email") String email, @Param("cpfCnpj") String cpfCnpj);
}
