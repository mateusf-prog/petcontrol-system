package com.mateus.petcontrolsystem.infra.exceptions;

public class TokenCreationException extends RuntimeException {

    public TokenCreationException(String message) {
        super(message);
    }
}
