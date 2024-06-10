package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.Roles;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class User extends Person {

    private Long id;
    private String password;
    private String cpfCnpj;

    private Roles role;

    private Address address;

    public User(String name, String email, String phone, LocalDate birthDate, Long id, String password, String cpfCnpj, Address address) {
        super(name, email, phone, birthDate);
        this.id = id;
        this.password = password;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
    }
}
