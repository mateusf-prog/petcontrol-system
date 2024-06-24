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
    private String userEmail;
    private String recoveryCode;
    @Column(columnDefinition = "TIMESTAMP")
    private Instant codeCreatedAt;
}