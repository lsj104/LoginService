package com.assignment.domain.global.exception;

import static com.assignment.domain.global.response.ExceptionResponse.of;

import com.assignment.domain.global.response.ExceptionResponse;
import com.assignment.domain.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> handleUserException(UserException e) {

        return ResponseEntity.status(e.getHttpStatus()).body(of(e.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(of(e.getLocalizedMessage()));
    }
}
