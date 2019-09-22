package dev.codenation.logs.exception.message.user;

import dev.codenation.logs.exception.message.model.AbstractNotFoundMessage;

public class UserNotFoundMessage extends AbstractNotFoundMessage {

    @Override
    public String getMessage() {
        return "I'm sorry, your user was not found on our database. Did you want to register now?";
    }
}