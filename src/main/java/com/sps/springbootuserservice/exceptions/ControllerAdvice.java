package com.sps.springbootuserservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(UserAlreadyExistsException userAlreadyExistsException) {
        return new ResponseEntity<>(
                new ExceptionDto(HttpStatus.BAD_REQUEST, userAlreadyExistsException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
