package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.models.Address;
import com.mateus.petcontrolsystem.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {

    private Long id;
    private String name;
    private String email;
    private String cpfCnpj;
    private String phone;
    private LocalDate birthDate;
    private String password;
    private AddressDTO address;

    public UserRegisterDTO(User entity ) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.cpfCnpj = entity.getCpfCnpj();
        this.phone = entity.getPhone();
        this.birthDate = entity.getBirthDate();
        this.password = entity.getPassword();
        this.address = new AddressDTO(entity.getAddress());
    }
}
