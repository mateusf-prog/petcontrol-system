package com.mateus.petcontrolsystem.services.exceptions;

public class ExpiredCodeException extends RuntimeException {

    public ExpiredCodeException(String message) {
        super(message);
    }
}
