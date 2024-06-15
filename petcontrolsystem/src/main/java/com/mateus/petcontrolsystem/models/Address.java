package com.mateus.petcontrolsystem.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private Integer cep;
    private String street;
    private Integer number;
    private String district;
    private String city;
    private String uf;
}
