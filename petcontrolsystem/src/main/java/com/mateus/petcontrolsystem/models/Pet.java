package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.AnimalType;
import com.mateus.petcontrolsystem.models.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {

    private Long id;
    private String name;
    private Double weight;
    private LocalDate birthDate;
    private String description;

    private AnimalType type;
    private Gender gender;

    private List<HealthCareLog> treatments = new ArrayList<>();
    private Client client;
}
