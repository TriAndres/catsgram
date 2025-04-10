package com.practicum.catsgram.exception;

public class DuplicatedDateException extends RuntimeException {
    public DuplicatedDateException(String message) {
        super(message);
    }
}