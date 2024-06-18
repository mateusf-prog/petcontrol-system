package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.models.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class UserLoginDTO {

    private String username;
    private String password;

    public UserLoginDTO(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
    }
}
