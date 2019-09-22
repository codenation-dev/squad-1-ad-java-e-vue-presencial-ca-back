package dev.codenation.logs.exception.message.user;

import dev.codenation.logs.exception.message.model.AbstractConflictExistsMessage;

public class UserExistsMessage extends AbstractConflictExistsMessage {

    @Override
    public String getMessage() {
        return "Hey, that user was created on our database, you want to login now?";
    }
}
