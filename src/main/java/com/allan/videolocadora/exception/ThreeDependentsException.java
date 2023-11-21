package com.allan.videolocadora.exception;

import java.io.Serial;

public class ThreeDependentsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ThreeDependentsException() {
        super("Partner can't have more than 3 active dependents!");
    }

    public ThreeDependentsException(String message) {
        super(message);
    }
}
