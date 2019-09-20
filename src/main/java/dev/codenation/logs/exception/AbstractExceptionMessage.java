package dev.codenation.logs.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractExceptionMessage extends Exception {

    protected String message;
    protected HttpStatus httpStatus;

    public AbstractExceptionMessage(String message) {
        this.message = message;
    }


    public String getMessage(){
        return message;
    }
}
