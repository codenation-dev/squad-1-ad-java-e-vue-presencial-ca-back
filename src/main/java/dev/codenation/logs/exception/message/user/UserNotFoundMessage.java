package dev.codenation.logs.exception.message.user;

import dev.codenation.logs.exception.message.model.AbstractNotFoundMessage;

public class UserNotFoundMessage extends AbstractNotFoundMessage {

    public UserNotFoundMessage() {
        super("USER_DOES_NOT_EXISTS");
    }
}