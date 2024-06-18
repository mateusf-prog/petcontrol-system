package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterReturnDTO {

    private Long id;
    private String name;
    private String email;
    private String cpfCnpj;
    private String phone;
    private LocalDate birthDate;
    private AddressDTO address;

    public UserRegisterReturnDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.cpfCnpj = entity.getCpfCnpj();
        this.phone = entity.getPhone();
        this.birthDate = entity.getBirthDate();
        this.address = new AddressDTO(entity.getAddress());
    }
}
