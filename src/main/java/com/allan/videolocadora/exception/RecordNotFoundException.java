package com.allan.videolocadora.exception;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException() {
        super("Record not found!");
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
