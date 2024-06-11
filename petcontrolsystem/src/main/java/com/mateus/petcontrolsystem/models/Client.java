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
@Entity()
@Table(name = "tb_clients")
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;

    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;

    @OneToOne
    private Address address;
//    private List<Agenda> appointments = new ArrayList<>();
//    private List<Order> orders = new ArrayList<>();
//    private List<Pet> pets = new ArrayList<>();
}
