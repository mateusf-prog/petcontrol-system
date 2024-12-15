package com.mateus.petcontrolsystem.controllers.handlers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mateus.petcontrolsystem.dto.StandardError;
import com.mateus.petcontrolsystem.infra.exceptions.TokenCreationException;
import com.mateus.petcontrolsystem.services.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardError> invalidPassword(InvalidPasswordException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<StandardError> entityExists(EntityAlreadyExistsException e, HttpServletRequest request) {
        var status = HttpStatus.CONFLICT;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TokenCreationException.class)
    public ResponseEntity<StandardError> creationTokenException(TokenCreationException e, HttpServletRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<StandardError> jsonMappingException (JsonMappingException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(ExpiredCodeException.class)
    public ResponseEntity<StandardError> codeExpired (ExpiredCodeException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<StandardError> invalidCode (InvalidCodeException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(InvalidProcessRecoveryPasswordException.class)
    public ResponseEntity<StandardError> invalidProcessRecoveryPassword (InvalidProcessRecoveryPasswordException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> invalidField (MethodArgumentNotValidException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        var err = new StandardError(Instant.now(), status.value(), errorMessage, request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<StandardError> mailSendException (MailSendException e, HttpServletRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<StandardError> mailAuthenticateException (MailAuthenticationException e, HttpServletRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(MailPreparationException.class)
    public ResponseEntity<StandardError> mailPreparationException (MailPreparationException e, HttpServletRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }
}
