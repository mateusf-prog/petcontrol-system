package com.mateus.petcontrolsystem.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Client extends Person {

    private Long id;
    private String cpf;

    private Address address;
    private List<Agenda> appointments = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Pet> pets = new ArrayList<>();
}
