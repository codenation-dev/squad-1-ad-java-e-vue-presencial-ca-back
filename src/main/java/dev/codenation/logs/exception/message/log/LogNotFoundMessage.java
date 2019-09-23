package dev.codenation.logs.exception.message.log;

import dev.codenation.logs.exception.message.model.AbstractNotFoundMessage;

public class LogNotFoundMessage extends AbstractNotFoundMessage {

    public LogNotFoundMessage() {
        super("LOG_DOES_NOT_EXISTS");
    }
}
