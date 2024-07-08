package com.mateus.petcontrolsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_recovery_password")
public class PasswordRecovery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String recoveryCode;
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private Instant codeCreatedAt;
}