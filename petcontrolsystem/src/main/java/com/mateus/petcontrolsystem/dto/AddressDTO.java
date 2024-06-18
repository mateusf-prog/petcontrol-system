package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.models.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

    private Integer zipCode;
    private String street;
    private Integer number;
    private String district;
    private String city;
    private String uf;

    public AddressDTO(Address entity) {
        this.zipCode = entity.getZipCode();
        this.street = entity.getStreet();
        this.number = entity.getNumber();
        this.district = entity.getDistrict();
        this.city = entity.getCity();
        this.uf = entity.getUf();
    }

}
