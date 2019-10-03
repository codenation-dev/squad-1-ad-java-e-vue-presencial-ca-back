package dev.codenation.logs.core.exception.message.log;

import dev.codenation.logs.core.exception.message.model.AbstractCouldNotExecuteActionException;

public class LogMismatchIdsException extends AbstractCouldNotExecuteActionException {
    public LogMismatchIdsException() {
        super("LOG_ID_FAILURE");
    }
}
