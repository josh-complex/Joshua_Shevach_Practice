package com.trilogyed.stwitterservice.controller;

import com.trilogyed.stwitterservice.exception.NotFoundException;
import com.trilogyed.stwitterservice.model.CustomErrorResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<CustomErrorResponse>> newResponseErrors(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                e.getBindingResult().getFieldErrors().stream()
                        .map(x -> new CustomErrorResponse(
                                HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                                x.getDefaultMessage(),
                                LocalDateTime.now())
                        )
                        .collect(Collectors.toList()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, NumberFormatException.class, DataAccessException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> outOfRangeException(Exception e) {
        return new ResponseEntity<>(new CustomErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                LocalDateTime.now()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> notFoundException(Exception e) {
        return new ResponseEntity<>(new CustomErrorResponse(
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}
