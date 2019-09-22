package dev.codenation.logs.exception.message.user;

import dev.codenation.logs.exception.message.model.AbstractConflictExistsMessage;

public class UserExistsMessage extends AbstractConflictExistsMessage {

    public UserExistsMessage(String message) {
        super("USER_EXISTS");
    }
}
