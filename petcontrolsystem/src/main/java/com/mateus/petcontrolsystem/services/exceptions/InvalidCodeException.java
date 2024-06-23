package com.mateus.petcontrolsystem.services.exceptions;

public class InvalidCodeException extends RuntimeException {

    public InvalidCodeException(String message) {
        super(message);
    }
}
