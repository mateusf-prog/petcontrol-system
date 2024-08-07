package com.mateus.petcontrolsystem.common;

import com.mateus.petcontrolsystem.dto.*;
import com.mateus.petcontrolsystem.models.Address;
import com.mateus.petcontrolsystem.models.User;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * This class provides constants for User entity. Used by AuthControllerTest, UserServiceTest and UserRepositoryTest
 * All the Objects here use the same values for his fields
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

    // these objects are used in UserServiceTest
    public static LoginRequestDTO getValidLoginRequestDTO() {
        return new LoginRequestDTO("jon.doe@hotmail.com", "Password1234");
    }

    public static UserAccessDataRequestDTO getValidUserAccessDataRequestDTO() {
        return new UserAccessDataRequestDTO("jon.doe@hotmail.com", "Password1234", "NewPassword4321");
    }

    public static RegisterRequestDTO getValidRegisterRequestDTO() {
        return new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148",
                LocalDate.of(1958, 6, 25), "Password1234",
                new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"));
    }

    public static UpdateUserDTO getValidUserUpdateDto() {
        return new UpdateUserDTO("Jon Doe", "12999999999", "50108953076",
                new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"));
    }

    // these objects are used in AuthControllerTest

    public static Stream<Arguments> provideInvalidUserUpdateDTO() {
        return Stream.of(
                Arguments.of(new UpdateUserDTO(null, "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", null, "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO(null, "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", null, "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", null, "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", null, "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", null, "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", null))),
                Arguments.of(new UpdateUserDTO("", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jo", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "invalid-phone", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "", "São José dos Campos", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "", "SP"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "invalid-uf"))),
                Arguments.of(new UpdateUserDTO("Jon Doe", "12991978448", "50108953076",
                        new AddressDTO("invalid-cep", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP")))

        );
    }

    public static Stream<Arguments> provideInvalidLoginRequestDTO() {
        return Stream.of(
                Arguments.of(new LoginRequestDTO(null, "Password@1234")),
                Arguments.of(new LoginRequestDTO("jon.doe@hotmail.com", null)),
                Arguments.of(new LoginRequestDTO("", "Password@1234")),
                Arguments.of(new LoginRequestDTO("jon.doe@hotmail.com", "")),
                Arguments.of(new LoginRequestDTO("invalid-email", "Password@1234")),
                Arguments.of(new LoginRequestDTO("jon.doe@hotmail.com", "invalidpasswordpattern"))
        );
    }

    public static Stream<Arguments> provideInvalidUserAccessDataRequestDTO() {
        return Stream.of(
                Arguments.of(new UserAccessDataRequestDTO("jondoehotmail.com", "Password@1234", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@.com", "Password@1234", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@@hotmail.com", "Password@1234", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hot mail.com", "Password@1234", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail..com", "Password@1234", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hot<mail>.com", "Password@1234", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "Pass12!", "Pass12!")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "Pas@34", "Pas@34")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "password@1234", "password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "PASSWORD@1234", "PASSWORD@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "Password@", "Password@")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "Password@1234", null)),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", null, "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("", "", "")),
                Arguments.of(new UserAccessDataRequestDTO("", "Password@1234", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "", "Password@1234")),
                Arguments.of(new UserAccessDataRequestDTO("jon.doe@hotmail.com", "Password@1234", ""))
        );
    }

    public static Stream<Arguments> provideInvalidRegisterRequestDTO() {
        return Stream.of(
                Arguments.of(new RegisterRequestDTO(null, "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", null, "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", null, "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", null, LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", null, "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), null,
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO(null, "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", null, "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", null, "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", null, "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", null, "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", null))),
                Arguments.of(new RegisterRequestDTO("", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "invalid-email", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("invalid-cep", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(2025, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "invalid-email", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "00000000000", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "invalidpasswordpattern",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jo", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "1234567", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(2024, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("0000", "Rua das Araras", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua", "170", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "aa", "Jardim Telespark", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Ja", "São José dos Campos", "SP"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "sa", "SP")),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "sa", "S"))),
                Arguments.of(new RegisterRequestDTO("Jon Doe", "jon.doe@hotmail.com", "50108953076", "12997825148", LocalDate.of(1958, 6, 25), "Password1234",
                        new AddressDTO("12212700", "Rua das Araras", "170", "Jardim Telespark", "sa", "SP")))));
    }

    public static GetUserDataResponseDTO validGetUserDataResponseDTO() {
        AddressDTO addressDTO = new AddressDTO("12212700", "Rua das Araras", "170",
                "Jardim Telespark", "sa", "SP");
        return new GetUserDataResponseDTO("Jon Doe", "email@teste.com", "50108953076", "12997825148",
                LocalDate.of(1980, 5, 25), addressDTO);
    }
}
