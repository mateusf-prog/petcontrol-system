package com.mateus.petcontrolsystem.common;

import com.mateus.petcontrolsystem.dto.*;
import com.mateus.petcontrolsystem.models.Address;
import com.mateus.petcontrolsystem.models.User;

import java.time.LocalDate;

/**
 * This class provides constants for User entity and DTOs related tests.
 * All the Objects here use the same values
 */
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
        user.setName("Jon Doe");
        user.setEmail("email@teste.com");
        user.setCpfCnpj("50108953076");
        user.setPhone("1299999999");
        user.setPassword("Password1234");
        user.setBirthDate(LocalDate.of(1958, 6, 25));
        user.setAddress(address);

        return user;
    }

    public static User getInvalidUser() {
        return new User();
    }

    public static RegisterRequestDTO getValidRegisterRequestDTO() {
        return new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "1299999999",
                LocalDate.of(1958, 6, 25), "Password1234",
                new AddressDTO("Rua José Domingues", "170", "Centro", "São José dos Campos", "SP", "12212700"));
    }

    public static RegisterRequestDTO getInValidRegisterRequestDTO() {
        return new RegisterRequestDTO(null, null, null, null,
                null, null,
                new AddressDTO(null, null, null, null, null, null));
    }

    public static UpdateUserDTO getValidUserUpdateDto() {
        return new UpdateUserDTO("Jon Doe", "1299999999", "50108953076",
                new AddressDTO("Rua José Domingues", "170", "Centro", "São José dos Campos", "SP", "12212700"));
    }

    public static UpdateUserDTO getInValidUserUpdateDto() {
        return new UpdateUserDTO(null, null, null,
                new AddressDTO(null, null, null, null, null, null));
    }

    public static UserAccessDataRequestDTO getValidUserAccessDataDTO() {
        return new UserAccessDataRequestDTO("jon.doe@hotmail.com", "Password1234", "NewPassword4321");
    }
}
