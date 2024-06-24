package com.mateus.petcontrolsystem.services.exceptions;

public class InvalidProcessRecoveryPasswordException extends RuntimeException {

    public InvalidProcessRecoveryPasswordException(String message) {
        super(message);
    }
}
