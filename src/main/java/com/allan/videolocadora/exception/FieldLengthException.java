package com.allan.videolocadora.exception;

import java.io.Serial;

public class FieldLengthException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FieldLengthException() {
        super("Field is too short or too big!");
    }

    public FieldLengthException(String message) {
        super(message);
    }
}
