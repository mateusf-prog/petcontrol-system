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

    private String name;
    @Column(unique = true)
    private String email;
    private String phone;
    private String cpfCnpj;
    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;
}
