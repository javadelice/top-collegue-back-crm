package dev.web.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    // la méthode handleConflict est exécutée lorsqu'un contrôleur émet une exception présente
    // dans la liste définie par l'annotation @ExceptionHandler
    @ExceptionHandler(value = { HttpClientErrorException.class })
    protected ResponseEntity<Object> handleConflict(HttpClientErrorException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
