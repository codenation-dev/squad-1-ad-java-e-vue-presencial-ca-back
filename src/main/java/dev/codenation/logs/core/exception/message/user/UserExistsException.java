package dev.codenation.logs.core.exception.message.user;

import dev.codenation.logs.core.exception.message.model.AbstractConflictExistsException;

public class UserExistsException extends AbstractConflictExistsException {

    public UserExistsException() {
        super("USER_EXISTS");
    }
}
