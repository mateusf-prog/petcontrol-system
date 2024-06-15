package com.mateus.petcontrolsystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "tb_customers")
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets = new ArrayList<>();
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "customer")
    private List<Agenda> appointments = new ArrayList<>();

}
