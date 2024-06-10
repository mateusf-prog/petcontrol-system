package com.mateus.petcontrolsystem.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    private Long id;
    private Integer cep;
    private String street;
    private Integer number;
    private String district;
    private String city;
    private String uf;

    private User user;

    private Client client;
}
