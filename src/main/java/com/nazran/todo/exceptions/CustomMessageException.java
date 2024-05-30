package com.nazran.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomMessageException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 14L;


    public CustomMessageException(String s) {
        super(s);
    }

}
