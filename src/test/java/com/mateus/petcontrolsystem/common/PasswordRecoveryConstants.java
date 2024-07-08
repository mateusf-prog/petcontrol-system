package com.mateus.petcontrolsystem.common;

import com.mateus.petcontrolsystem.dto.password.CodeReceivedInEmailRequestDTO;
import com.mateus.petcontrolsystem.dto.password.EmailToRecoverPasswordDTO;
import com.mateus.petcontrolsystem.dto.password.NewPasswordToRecoveryAccountDTO;
import com.mateus.petcontrolsystem.models.PasswordRecovery;
import org.junit.jupiter.params.provider.Arguments;

import java.time.Instant;
import java.util.stream.Stream;

public class PasswordRecoveryConstants {

    public static EmailToRecoverPasswordDTO getValidEmailToRecoverPasswordDTO() {
        return new EmailToRecoverPasswordDTO("jon.doe@hotmail.com");
    }

    public static PasswordRecovery getValidPasswordEntity() {
        PasswordRecovery entity = new PasswordRecovery();
        entity.setRecoveryCode("25365");
        entity.setUserEmail("jon.doe@hotmail.com");
        entity.setCodeCreatedAt(Instant.now());
        return entity;
    }

    public static EmailToRecoverPasswordDTO getInvalidEmailToRecoverPasswordDTO() {
        return new EmailToRecoverPasswordDTO("");
    }

    public static CodeReceivedInEmailRequestDTO getValidCodeReceivedEmailRequestDTO() {
        return new CodeReceivedInEmailRequestDTO("jon.doe@hotmail.com", "12345");
    }

    public static NewPasswordToRecoveryAccountDTO getValidNewPasswordToRecoveryAccountDTO() {
        return new NewPasswordToRecoveryAccountDTO("jon.doe@hotmail.com", "Newpassword@12345");
    }

    public static Stream<Arguments> provideInvalidEmailToRecoverPasswordDTO() {
        return Stream.of(
                Arguments.of(new EmailToRecoverPasswordDTO("")),
                Arguments.of(new EmailToRecoverPasswordDTO("invalid-email")),
                Arguments.of(new EmailToRecoverPasswordDTO(null)));
    }
}
