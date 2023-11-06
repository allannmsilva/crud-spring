package com.allan.videolocadora.exception;

import java.io.Serial;

public class IntegrityConstraintException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IntegrityConstraintException() {
        super("Record not found!");
    }

    public IntegrityConstraintException(String message) {
        super(message);
    }
}
