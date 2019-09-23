package dev.codenation.logs.exception.message.model;

import dev.codenation.logs.exception.message.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AbstractCouldNotExecuteAction extends AbstractExceptionMessage {

    public AbstractCouldNotExecuteAction(String message) {
        super(message);
    }
}
