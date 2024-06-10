package com.mateus.petcontrolsystem.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
}
