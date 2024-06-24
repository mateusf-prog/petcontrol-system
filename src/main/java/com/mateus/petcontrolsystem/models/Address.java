package com.mateus.petcontrolsystem.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private String zipCode;
    private String street;
    private String number;
    private String district;
    private String city;
    private String uf;
}
