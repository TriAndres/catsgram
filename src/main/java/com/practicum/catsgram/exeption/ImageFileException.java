package com.practicum.catsgram.exeption;

public class ImageFileException extends RuntimeException{
    public ImageFileException(String message) {
        super(message);
    }

    public ImageFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
