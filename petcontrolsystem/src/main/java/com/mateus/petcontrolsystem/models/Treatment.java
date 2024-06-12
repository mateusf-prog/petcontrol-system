package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.TreatmentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_health_care_log")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate applicationDate;
    private String details;

    @Enumerated(EnumType.STRING)
    private TreatmentType type;

    @ManyToOne
    private Pet pet;
}
