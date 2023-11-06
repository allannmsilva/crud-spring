package com.allan.videolocadora.controller;

import com.allan.videolocadora.exception.FieldLengthException;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException notFoundException) {
        return notFoundException.getMessage();
    }

    @ExceptionHandler(FieldLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFieldLengthException(FieldLengthException fieldLengthException) {
        return fieldLengthException.getMessage();
    }

    @ExceptionHandler(RequiredFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRequiredFieldException(RequiredFieldException requiredFieldException) {
        return requiredFieldException.getMessage();
    }

    @ExceptionHandler(IntegrityConstraintException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public String handleIntegrityConstraintException(IntegrityConstraintException integrityConstraintException) {
        return integrityConstraintException.getMessage();
    }
}
