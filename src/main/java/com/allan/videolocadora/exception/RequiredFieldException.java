package com.allan.videolocadora.exception;

import java.io.Serial;

public class RequiredFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RequiredFieldException(){
        super("You must enter a value for this field!");
    }

    public RequiredFieldException(String message) {
        super(message);
    }
}
