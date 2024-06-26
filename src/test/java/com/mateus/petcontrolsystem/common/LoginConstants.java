package com.mateus.petcontrolsystem.common;

import com.mateus.petcontrolsystem.dto.LoginRequestDTO;
import com.mateus.petcontrolsystem.models.User;

public class LoginConstants {

    public static final LoginRequestDTO LOGIN = new LoginRequestDTO("email@hotmail.com", "Password@1234");
    public static final LoginRequestDTO INVALID_LOGIN = new LoginRequestDTO("", "");

    public static User convertDtoToUser(LoginRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }
}
