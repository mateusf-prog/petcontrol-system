package com.mateus.petcontrolsystem.services;

import com.mateus.petcontrolsystem.dto.password.CodeReceivedEmailResponseDTO;
import com.mateus.petcontrolsystem.dto.password.CodeReceivedInEmailRequestDTO;
import com.mateus.petcontrolsystem.dto.password.EmailToRecoverPasswordDTO;
import com.mateus.petcontrolsystem.dto.password.NewPasswordToRecoveryAccountDTO;
import com.mateus.petcontrolsystem.infra.security.TokenService;
import com.mateus.petcontrolsystem.models.PasswordRecovery;
import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.repositories.PasswordRecoveryRepository;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.exceptions.ExpiredCodeException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidCodeException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidProcessRecoveryPasswordException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {

    private final PasswordRecoveryRepository repository;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void sendCodeToEmail(EmailToRecoverPasswordDTO body) {

        User user = userRepository.findByEmail(body.email()).orElseThrow(
                () -> new EntityNotFoundException("USER NOT FOUND"));

        String randomCode = generateCodeToSendToEmail();

        PasswordRecovery passwordRecovery = new PasswordRecovery();
        passwordRecovery.setUserEmail(user.getEmail());
        passwordRecovery.setRecoveryCode(randomCode);
        passwordRecovery.setCodeCreatedAt(LocalDateTime.now().toInstant(ZoneOffset.of("-3")));
        repository.save(passwordRecovery);

        emailService.sendCodeToEmail(randomCode, body.email());
    }

    @Transactional
    public CodeReceivedEmailResponseDTO validateCodeReceivedInEmail(CodeReceivedInEmailRequestDTO body) {

        User user = userRepository.findByEmail(body.email()).orElseThrow(
                () -> new EntityNotFoundException("USER NOT FOUND"));

        Optional<PasswordRecovery> existingPasswordRecovery = Optional.ofNullable(repository.findByUserEmail(user.getEmail()).orElseThrow(
                () -> new InvalidProcessRecoveryPasswordException("INVALID REQUEST")
        ));
        validateCodeReceived(body, existingPasswordRecovery.get());

        String token = tokenService.generateTemporaryTokenToRecoveryPassword(user);

        existingPasswordRecovery.get().setRecoveryCode(null);
        existingPasswordRecovery.get().setCodeCreatedAt(null);
        repository.save(existingPasswordRecovery.get());
        return new CodeReceivedEmailResponseDTO(token);
    }

    @Transactional
    public EmailToRecoverPasswordDTO setNewUserPassword(NewPasswordToRecoveryAccountDTO body) {

        // if token is valid...
        User user = userRepository.findByEmail(body.email()).orElseThrow(
                () -> new EntityNotFoundException("USER NOT FOUND"));

        user.setPassword(passwordEncoder.encode(body.newPassword()));
        userRepository.save(user);
        emailService.sendEmailNewPasswordSet(user.getEmail(), user.getName());
        return new EmailToRecoverPasswordDTO(user.getEmail());
    }

    public String generateCodeToSendToEmail() {
        var CODE_LENGTH = 5;
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < 5; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        return code.toString();
    }

    public void validateCodeReceived(CodeReceivedInEmailRequestDTO body, PasswordRecovery passwordRecovery) {
        if (passwordRecovery.getRecoveryCode() == null)
            throw new InvalidCodeException("INVALID REQUEST");
        if (!passwordRecovery.getRecoveryCode().equals(body.code()))
            throw new InvalidCodeException("INVALID CODE");
        if (passwordRecovery.getCodeCreatedAt().isBefore(LocalDateTime.now().minusMinutes(10).toInstant(ZoneOffset.of("-3")))) {
            throw new ExpiredCodeException("CODE EXPIRE");
        }
    }
}
