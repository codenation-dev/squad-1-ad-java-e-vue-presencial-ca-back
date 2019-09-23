package dev.codenation.logs.exception.message.log;

import dev.codenation.logs.exception.message.model.AbstractCouldNotExecuteAction;

public class LogCouldNotBeArchivedMessage extends AbstractCouldNotExecuteAction {
    public LogCouldNotBeArchivedMessage() {
        super("LOG_COULD_NOT_BE_ARCHIVED");
    }
}
