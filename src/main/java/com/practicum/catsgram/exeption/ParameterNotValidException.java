package com.practicum.catsgram.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParameterNotValidException extends IllegalArgumentException {
    private final String parameter;
    private final String reason;

    public ParameterNotValidException(String parameter, String reason) {
        super("Неверное указано значение параметра [" + parameter + "]: " + reason);
        this.parameter = parameter;
        this.reason = reason;
    }
}
