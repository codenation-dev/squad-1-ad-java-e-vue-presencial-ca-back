package dev.codenation.logs.core.exception.message.log;

import dev.codenation.logs.core.exception.message.model.AbstractNotFoundException;

public class LogNotFoundException extends AbstractNotFoundException {

    public LogNotFoundException() {
        super("LOG_DOES_NOT_EXISTS");
    }
}
