package com.mateus.petcontrolsystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String uf;
}
