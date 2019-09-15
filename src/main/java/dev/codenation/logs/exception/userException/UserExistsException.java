package dev.codenation.logs.exception.userException;


import org.springframework.http.HttpStatus;

public class UserExistsException extends RuntimeException {

    public HttpStatus httpError() {
        return HttpStatus.CONFLICT;
    }
}
