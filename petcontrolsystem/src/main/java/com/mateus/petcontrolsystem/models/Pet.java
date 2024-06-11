package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.AnimalType;
import com.mateus.petcontrolsystem.models.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity()
@Table(name = "tb_pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double weight;
    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private AnimalType type;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "pet")
    private List<HealthCareLog> treatments = new ArrayList<>();
}
