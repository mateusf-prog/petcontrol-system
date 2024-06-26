package com.mateus.petcontrolsystem.common;

import com.mateus.petcontrolsystem.models.Address;
import com.mateus.petcontrolsystem.models.User;

import java.time.LocalDate;

public class UserConstants {

    public static User getValidUser() {

        Address address = new Address();
        address.setCity("São José dos Campos");
        address.setUf("SP");
        address.setDistrict("Centro");
        address.setNumber("170");
        address.setZipCode("12212700");
        address.setStreet("Rua José Domingues");
        User user = new User();

        user.setName("test-name");
        user.setEmail("email@teste.com");
        user.setCpfCnpj("11122233344");
        user.setPhone("1299999999");
        user.setPassword("Password1234");
        user.setBirthDate(LocalDate.of(1958, 6, 25));
        user.setAddress(address);

        return user;
    }

    public static User getInvalidUser() {
        return new User();
    }
}
