package dev.codenation.logs.exception.users;

import dev.codenation.logs.exception.AbstractExceptionMessage;

public class UserNotFoundException extends AbstractExceptionMessage {

    public UserNotFoundException(String message) {
        super(message);
    }
}
