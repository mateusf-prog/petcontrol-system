package com.mateus.petcontrolsystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {

    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(unique = true, nullable = false)
    private String cpfCnpj;
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birthDate;
}
