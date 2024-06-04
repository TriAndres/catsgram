package com.practicum.catsgram.controller;

import com.practicum.catsgram.exeption.ConditionsNotMetException;
import com.practicum.catsgram.exeption.DuplicatedDataException;
import com.practicum.catsgram.exeption.NotFondException;
import com.practicum.catsgram.exeption.ParameterNotValidException;
import com.practicum.catsgram.model.ErrorResponse;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(final ParameterNotValidException e) {
        return new ErrorResponse(
               String.format("Неккоректное значение параметра %s: %s", e.getParameter(), e.getReason())
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleConditionsNotMetException(final ConditionsNotMetException e) {
        return  new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFondException(final NotFondException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedDataException(final DuplicatedDataException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable ignored) {
        return new ErrorResponse(
              "Произошла непредвиденная ошибка."
        );
    }

}
