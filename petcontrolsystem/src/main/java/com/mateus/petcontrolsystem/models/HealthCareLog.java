package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.TreatmentType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class HealthCareLog {

    private Long id;
    private LocalDate applicationDate;
    private String details;

    private TreatmentType type;

    private Pet pet;
}
